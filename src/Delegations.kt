fun main(args: Array<String>) {
    Class1().callClass2()
}

interface ValueInterface {
    fun print()
    fun printMessage()
    fun printMessageLine()
}

/**
 * In delegation we can pass context of interface and implement only methods which are required . In class 2 only print() is over ridden
 * so, calling print() from class2 obj will print2 from class2 and other interface methods will be called from class1
 * */
class Class1 : ValueInterface {

    fun callClass2() {
        Class2(this).printClass1()

    }

    override fun print() {
        println("override print() Class1")
    }

    override fun printMessage() {
        println("override printMessage() Class1")
    }

    override fun printMessageLine() {
        println("override printMessageLine() Class1")
    }
}

class Class2(private var interfaceVal: ValueInterface) : ValueInterface by interfaceVal {

    fun printClass1() {
        print()
        printMessage()
        printMessageLine()
    }

    override fun print() {
        println("override print() Class2 ")
    }

}


/**
 * If there’s a class B which implements interface A, you can delegate all methods of b to class C by interface A. It’s called Class Delegation.
 * Internally, b will be stored as the private field in C, and all implemented methods of B will be generated as static methods which refer to that private field b in C
 * */

