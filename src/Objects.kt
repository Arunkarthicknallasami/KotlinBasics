import sample.singleton.strSingleton

/**
 * Objects are mainly used for singleton and they are independent of class they are in and are initialised only when invokes
 * Objects can inherit other class and extend an interface and can have init block
 * Objects cannot have an constructor
 *
 * Companion objects are part of a class
 * Members inside companion object can be accessed like static items in java
 * */

fun main(args: Array<String>) {
    var first = Singleton.instance  // This (Singleton@7daf6ecc) is a
    // singleton
    first.b = "hello singleton"

    var second = Singleton.instance
    println(second.b)
    second.printContext()


    var first2 = Singleton2
    first2.b = "hello singleton2"
    var second2 = Singleton2
    println(second2.b)
    second2.printContext2()


//    val sample = sample
//    println("sample values are ${sample.callPrint()}")
    sample().callPrint()
    sample.printCompanion()
}

/**
 * companion object has these characteristics:
-Unable to stand by itself
-Is loaded with the class

object has these characteristics:
-Able to stand by itself
-Can be used as an expression
-Used for anonymous inner class equivalent
-Lazy
 *
 * **/
/**Type 1 for singleton*/
class Singleton private constructor() {
    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = Singleton()
    }

    companion object {
        val instance: Singleton by lazy { Holder.INSTANCE }
    }

    var b: String? = null

    fun printContext() {
        println("This ($this) is a singleton instance")
    }
}

/************************************************************/
object Singleton2 {
    init {
        println("This ($this) is a singleton")
    }

    var b: String? = null

    fun printContext2() {
        println("This ($this) is a singleton instance")
    }
}

/**********************************************************/

/**Accessing values inside object and companion object, also accessing values from object and companion objects inside a class*/
class sample {
    val strOuter = "value from outer class"

    object singleton {
        val strSingleton = "value from singleton class"
        fun printOuter() {
            println("$strSingleton")
        }
    }

    companion object {
        var companionClass = "value from companion object"
        fun printCompanion() {
            println("$companionClass")
        }
    }

    fun callPrint() {
        companionClass = "value updated"
        println("values are ${strOuter}, ${strSingleton},${companionClass.toUpperCase()}")
    }
}