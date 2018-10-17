import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
    var coroutine = couroutineFunction()
    cancelEvents()
    Timeouts()
//    coroutineChannels()
    sequentialFunctions()
    concurrentFunctions()
    dispatchers()
//    exceptions()
}

//function is turned into a coroutine using runBlocking coroutine builder.
/*Every coroutine builder, including runBlocking, adds an instance of CoroutineScope to the scope its code block.
We can launch coroutines in this scope without having to join them explicitly,
because an outer coroutine (runBlocking in our example) does not complete until all the coroutines launched in its scope complete*/
fun couroutineFunction() = runBlocking {
    // start main coroutine
    /*Active coroutines that were launched in GlobalScope do not keep the process alive. They are like daemon threads.*/
    var global = GlobalScope.launch {
        // launch new coroutine in background and continue
        delay(2000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    global.join()

    /*In addition to the coroutine scope provided by different builders, it is possible to declare your own scope using coroutineScope builder.
     It creates new coroutine scope and does not complete until all launched children complete.
     The main difference between runBlocking and coroutineScope is that the latter does not block
     the current thread while waiting for all children to complete.*/
    coroutineScope {
        // Creates a new coroutine scope and non blocking
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before nested launch
    }
    repeat(100) {
        // launch a lot of coroutines
        launch {
            delay(1000L)
            print(".")
        }
    }
    doWorld()
}

/*Suspending functions can be used inside coroutines just like regular functions, but their additional feature is that they can,
 in turn, use other suspending functions, like delay in this example, to suspend execution of a coroutine*/
suspend fun doWorld() {
    delay(1000L)
    println("suspend function!")
}

/*Coroutine cancellation is cooperative. A coroutine code has to cooperate to be cancellable.
 All the suspending functions in kotlinx.coroutines are cancellable.
 They check for cancellation of coroutine and throw CancellationException when cancelled.
 However, if a coroutine is working in a computation and does not check for cancellation, then it cannot be cancelled. It can be cancelled by checking "isActive" */
//Cancel a coroutine
fun cancelEvents() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        try {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // cancellable computation loop
                // print a message twice a second
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        } finally {
            /*Any attempt to use a suspending function in the finally block of the previous example causes CancellationException,
             because the coroutine running this code is cancelled. Usually, this is not a problem,
             since all well-behaving closing operations (closing a file, cancelling a job, or closing any kind of a communication channel)
             are usually non-blocking and do not involve any suspending functions. However, in the rare case when you need to suspend in the cancelled coroutine
             you can wrap the corresponding code in withContext(NonCancellable) {...} using withContext function and NonCancellable context */
            withContext(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    job.join() // waits for job's completion
    job.cancelAndJoin() // combines cancel() and join() into a single function that cancels the job and waits for its completion
    println("main: Now I can quit.")
}

//Timeout
fun Timeouts() = runBlocking(Dispatchers.Default) {
    /*We can also use withTimeout but we get TimeoutCancellationException*/
    withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm Timeout $i ...")
            delay(500L)
        }
    }
}

fun coroutineChannels() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
    // here we print five received integers:
    for (y in channel) {
        println(channel.receive())
    }
    println("Done!")

}

/*sequential functions*/
fun sequentialFunctions() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

/*Conceptually, async is just like launch. It starts a separate coroutine which is a light-weight thread that works concurrently with all the other coroutines.
 The difference is that launch returns a Job and does not carry any resulting value, while async returns a Deferred – a light-weight non-blocking future
 that represents a promise to provide a result later.
 You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job, so you can cancel it if needed.*/
/*Concurrent functions*/
fun concurrentFunctions() = runBlocking<Unit> {
    val time = measureTimeMillis {
        /*There is a laziness option to async using an optional start parameter with a value of CoroutineStart.LAZY.
        It starts coroutine only when its result is needed by some await or if a start function is invoked. */
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

/*Dispatchers and threads*/
fun dispatchers() = runBlocking<Unit> {
    launch {
        // context of the parent, main runBlocking coroutine
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    /*The Dispatchers.Unconfined coroutine dispatcher starts coroutine in the caller thread, but only until the first suspension point.
    After suspension it resumes in the thread that is fully determined by the suspending function that was invoked.
    Unconfined dispatcher is appropriate when coroutine does not consume CPU time nor updates any shared data (like UI) that is confined to a
    specific thread.*/
    launch(Dispatchers.Unconfined) {
        // not confined -- will work with main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        // will get dispatched to DefaultDispatcher
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        // will get its own new thread
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
}

/*Exceptions handling*/
fun exceptions() = runBlocking {
    val job = GlobalScope.launch {
        println("Throwing exception from ${Thread.currentThread().name}}")
        throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
    }
    job.join()
    println("Joined failed job")
    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException() // Nothing is printed, relying on user to call await
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
}

/*Select expression*/
