
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel

fun main(args: Array<String>)  = runBlocking{
    var coroutine = couroutineFunction()
    cancelEvents()
    Timeouts()
    coroutineChannels()
}

//function is turned into a coroutine using runBlocking coroutine builder.
fun couroutineFunction() = runBlocking {
    // start main coroutine
    GlobalScope.launch {
        // launch new coroutine in background and continue
        delay(2000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed

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
            /*in the rare case when you need to suspend in the cancelled coroutine you can wrap the corresponding code in withContext(NonCancellable)*/
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
    for(y in channel) { println(channel.receive()) }
    println("Done!")

}
