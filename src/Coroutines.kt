import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.coroutineContext
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking<Unit> {
    /**launch*/
    val job = launch {
        // launch new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    /**next set of code will wait as a result of job.join() for given delay**/
    job.join()

    val job2 = launch {
        /**Active coroutines do not keep the process alive. They are like daemon threads.*/
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job2.cancel() // cancels the job
    job2.join() // waits for job's completion
    println("main: Now I can quit.")

    /**Launch many coroutines*/
    val jobs = List(100) {
        // launch a lot of coroutines and list their jobs
        launch {
            delay(1000L)
            print(".")
        }
    }
    jobs.forEach { it.join() } // wait for all jobs to complete


    val startTime = System.currentTimeMillis()
    val job3 = launch {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job3.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")

    /**Timeout*/
    /** This will throw an exception instead use withTimeoutorNull
     *
     * withTimeout(1300L) {
    repeat(1000) { i ->
    println("I'm sleeping $i ...")
    delay(500L)
    }
    }
     **/
    withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    /**Async*/
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        /**wait for async operation to get completed and then proceed*/
        one.await()
        println("Completed value is ${one.getCompleted()}")
        println("The answer is ${one.await() + two.await()}")


    }
    println("Completed in $time ms")

/**Async task will be invoked only when is needed by some await or if a start function is invoked*/
    val waitAsync = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $waitAsync ms")

    /**Launching list of coroutines*/
    val jobsCollection = arrayListOf<Job>()
    jobsCollection += launch(Unconfined) { // not confined -- will work with main thread
        println("      'Unconfined': I'm working in thread ${Thread.currentThread().name}")
    }
    jobsCollection += launch(coroutineContext) { // context of the parent, runBlocking coroutine
        println("'coroutineContext': I'm working in thread ${Thread.currentThread().name}")
    }
    jobsCollection += launch(CommonPool) { // will get dispatched to ForkJoinPool.commonPool (or equivalent)
        println("      'CommonPool': I'm working in thread ${Thread.currentThread().name}")
    }
    jobsCollection += launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("          'newSTC': I'm working in thread ${Thread.currentThread().name}")
    }
    jobsCollection.forEach { it.join() }

    /**Unconfined launches*/
    val jobsUnconfined = arrayListOf<Job>()
    jobsUnconfined += launch(Unconfined) { // not confined -- will work with main thread
        println("      'Unconfined': I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("      'Unconfined': After delay in thread ${Thread.currentThread().name}")
    }
    jobsUnconfined += launch(coroutineContext) { // context of the parent, runBlocking coroutine
        println("'coroutineContext': I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("'coroutineContext': After delay in thread ${Thread.currentThread().name}")
    }
    jobsUnconfined.forEach { it.join() }

}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}