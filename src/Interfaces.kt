fun main(args: Array<String>) {
    val jack = Teacher("Jack Smith")
    jack.displayJob("I'm a mathematics teacher.")
    jack.displaySSN(23123)


//interface samples
    val obj = InterfaceImp()

    println("test = ${obj.test}")
    print("Calling hello(): ")

    obj.hello()

    print("Calling and printing foo(): ")
    println(obj.foo())
}
/***********************************************************/
//interface methods are abstract by default and all its methods and variables need to be overridden when inherited,
// but when body tag is added it will not be treated as abstract
interface MyInterface {
    val test: Int

    fun foo(): String

    fun hello() {
        println("Hello there, pal!")
    }

}

class InterfaceImp : MyInterface {

    override val test: Int = 25
    override fun foo() = "Lol"
}
/***********************************************************/
//abstract class has abstract method, member variables and member function by default will not be abstract it has to be explicitly specified
abstract class Friend(name: String) {

    init {
        println("My name is $name.")
    }

    fun displaySSN(ssn: Int) {
        println("My SSN is $ssn.")
    }

    abstract fun displayJob(description: String)
}

class Teacher(name: String) : Friend(name) {

    override fun displayJob(description: String) {
        println(description)
    }
}
/***********************************************************/

/**interface A {
fun foo() { print("A") }
fun bar()
}

interface B {
fun foo() { print("B") }
fun bar() { print("bar") }
}

class C : A {
override fun bar() { print("bar") }
}

class D : A, B {
override fun foo() {
super<A>.foo()
super<B>.foo()
}

override fun bar() {
super<B>.bar()
}
}

Interfaces A and B both declare functions foo() and bar(). Both of them implement foo(),
but only B implements bar() (bar() is not marked abstract in A, because this is the default for interfaces,
if the function has no body). Now, if we derive a concrete class C from A,
we, obviously, have to override bar() and provide an implementation.

However, if we derive D from A and B, we need to implement all the methods which
we have inherited from multiple interfaces, and to specify how exactly D should implement them.
This rule applies both to methods for which we've inherited a single implementation (bar()) and multiple implementations (foo()).

 */

/***********************************************************/