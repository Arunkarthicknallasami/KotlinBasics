fun main(args: Array<String>) {
    InitOrderDemo("hello")
    var person = ConstructorWithArguments("Suresh", 25, "Developer")
    person.printPersonDetails()
    ConstructorWithoutArguments(5)


    //inheritance
    val t1 = MathTeacher(25, "Jack")
    t1.teachMaths()

    println()

    val f1 = Footballer(29, "Christiano")
    f1.playFootball()

    val p1 = AuthLog("Bad Password")


    val d = Derived("hello", "world")


    val e = C()
    e.f()
}

/***********************************************************/
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

/***********************************************************/
//primary constructor with no arguments
class ConstructorWithoutArguments {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Secondary constructor")
    }
}

/***********************************************************/
//Primary Constructor with Arguments, when we add var or val in constructor arguments then those variables can be accessed inside class
class ConstructorWithArguments constructor(var name: String, var age: Int) {
    var profession: String = "Not Mentioned"

    init {
        println("Init block $name")
    }

    constructor (name: String, age: Int, profession: String) : this(name, age) {
        this.profession = profession
    }

    fun printPersonDetails() {
        println("$name whose profession is $profession, is $age years old.")
    }
}

/***********************************************************/
//inheritance - Class with primary constructor and arguments
open class Person(age: Int, name: String) {
    init {
        println("My name is $name.")
        println("My age is $age")
    }

    open fun displayAge(age: Int) {
        println("My age is $age.")
    }
}

class MathTeacher(age: Int, name: String) : Person(age, name) {

    fun teachMaths() {
        println("I teach in primary school.")
    }

    override fun displayAge(age: Int) {
        println("My fake age is ${age - 5}.")
    }
}

class Footballer(age: Int, name: String) : Person(age, name) {
    fun playFootball() {
        println("I play for LA Galaxy.")
    }
}

/***********************************************************/

//inheritance - Class with no primary constructor
open class Log {
    var data: String = ""
    var numberOfData = 0


    constructor(_data: String) {

    }

    constructor(_data: String, _numberOfData: Int) : this(_data) {
        data = _data
        numberOfData = _numberOfData
        println("$data: $numberOfData times")
    }
}

class AuthLog : Log {
    constructor(_data: String) : this("From AuthLog -> + $_data", 10) {
    }

    constructor(_data: String, _numberOfData: Int) : super(_data, _numberOfData) {
    }
}

/***********************************************************/
//overriding and functional flow in inheritance

open class Base(val name: String) {

    init {
        println("Initializing Base")
    }

    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
        name: String,
        val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init {
        println("Initializing Derived")
    }

    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

/***********************************************************/
//overriding when more than one instance of a method is found from inherited classes
open class A {
    open fun f() {
        print("A")
    }

    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    } // interface members are 'open' by default

    fun b() {
        print("b")
    }
}

class C() : A(), B {
    override fun b() {
    }

    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
        println("extended classes function")
    }
}

/***********************************************************/
//abstract class
open class Base2 {
    open fun f() {}
}

abstract class Derived2 : Base2() {
    override abstract fun f()
}

//here 3rd class extends an abstract class which implements base2 class's method and which gets overridden automatically
class Derived3 : Derived2() {
    override fun f() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/***********************************************************/

