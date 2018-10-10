import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

fun main(args: Array<String>) {

    /**'Also' - also passes an object as a parameter and returns the same object (not the result of the lambda!):*/

    val human = Human("Edmund", 42)
    val alsoResult = human.also { person -> person.age = 50 }

    println(human)
    println(alsoResult)


    /**'let' let is a non-monadic version of map: It accepts objects as parameters and returns the result of the lambda.*/

    val letResult = human.let { it.age * 2 }
    println(human)
    println(letResult)

    /**'apply' - apply is used for post-construction configuration. It is a function literal with receiver:
     *  The object is not passed as a parameter, but rather as this.
     *  An object passed in such way is called the receiver*/

    val applyResult = human.apply { age = 50 }
    println(human)
    println(applyResult)


    /**'run' - run is another function literal with receiver. It is used with lambdas that do not return values,
     *  but rather just create some side-effects*/


    val runResult = human.run { age * 2 }
    println(human)
    println(runResult)


    /**'with' - With does not work with nullable variables.*/

    var inhuman: Human? = Human("Edmund", 42)

    val withResult = with(inhuman) {
        this?.age?.times(2)
    }
    println(inhuman)
    println(withResult)
//    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L)

    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L)
}

data class Human(var name: String, var age: Int)
