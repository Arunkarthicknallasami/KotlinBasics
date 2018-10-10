fun main(args: Array<String>) {
    val externalClass = Another()
    println("i called from external ${externalClass.iValue()}")

}
//public can be accessed anywhere
//private can only be accessed inside a class
//protected can be accessed from sub class / Inherited class

//protected variable
open class Pr {
    //i can be accessed from extended class and own class
    protected val i = 0
    //j can only be access inside the class which owns it
    private val j = 1

    //private can be accessed inside class
    fun jvalue(): Int {
        return j
    }

}

 class Another : Pr() {

     fun iValue(): Int {
        return i
    }
    // j cannot be accessed outside
//    fun jValue2(): Int {
//        return j
//    }
}

/*Class Internal is only accessible from inside the same module. “val i” and “fun doSomething()”
are also only accessible from inside the same module, even though the class they are inside can be accessed from anywhere*/
internal class Internal {
}

class Public {
    internal val i = 1

    internal fun doSomething() {
    }
}
