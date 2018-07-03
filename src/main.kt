fun main(args: Array<String>) {

    val numbers = intArrayOf(1, 4, 42, -3)



    if (4 in numbers) {
        println("numbers array contains 4.")
    }

    val stringArray = charArrayOf('a', 'b')
    if ('b' in stringArray) {
        println("stringArray contains a")
    }
    println("size of string array is ${stringArray.size}")
    val asc = Array(5, { i -> (i * i).toString() })

    val s = "Hello, world!\n"
    if ('H' in s) {
        println(" Search found!!!")
    }

    val sample = "hey there i am so glad to have you".trim()

    val doubSample: Float = 45.5555F
    var intSample: Int = doubSample.toInt()
    println("float sample $intSample")
    val a = 10
    val b = 20
    val expression = if (a > b) {
        var c = 5;
        ++c;
    } else {
        var d = 10; ++d;
    }
    println("value of expression is $expression")

    var ab1: Int = 45
    var ab2: Int = 20
    var ab3 = if (ab1 > ab2) ab1 + ab2 else ab2 - ab1
    println("new ab3 value is $ab3")
    /*val reader = Scanner(System.`in`)
    print("Enter a number: ")

    // nextInt() reads the next integer from the keyboard
    var integer:Int = reader.nextInt()

    println("You entered: $integer")*/


//    val g = 12
//    val h = 5
//
//    println("Enter operator either +, -, * or /")
//    val operator = readLine()
//
//    val result = when (operator?.trim()) {
//        "+" -> g + h
//        "-" -> g - h
//        "*" -> g * h
//        "/" -> g / h
//        else -> "$operator operator is invalid operator."
//    }
//
//    println("result = $result")

    var arrayTraverse = intArrayOf(1, 2, 3, 4, 5)
    for (i in arrayTraverse) {
        println("i value is $i")
    }

    val number = 4
    val result: Long

    result = factorial(number)
    println("Factorial of $number = $result")
    val c: Int = add(2, 3)
    println("value of addition is $c")
    addWithoutReturn()
    println(displayName(firstName = "Arun", lastName = "karthick"))
    val p = Structure()
    p createPyramid 4

    println("Output when no argument is passed:")
    displayBorder()

    println("\n\n'*' is used as a first argument.")
    println("Output when first argument is passed:")
    displayBorder('*')

    println("\n\n'*' is used as a first argument.")
    println("5 is used as a second argument.")
    println("Output when both arguments are passed:")
    displayBorder('*', 5)


    val listWithNulls: List<String?> = listOf("A", null)
    for (item in listWithNulls) {
        item?.let { println(it) } // prints A and ignores null
    }
    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

    println("text $text")

    val address = Address()
    address.name = "Mohan"
    address.street = "Karur"
    var newAdress = copyAddress(address)
    println("new adress values are ${newAdress.name} and new street ${newAdress.street} and other details are ${newAdress.city} is salary details empty ${newAdress.isNull}")
    val superman = Hero()
    superman.savePlanet()
//    val printable = Printable()
//    printable.printValue(10, 20)

    val movie = Movie("Whiplash", "Sony Pictures", 8.5F)

    println(movie.name)   //Whiplash
    println(movie.studio) //Sony Pictures
    println(movie.rating) //8.5

    println(movie.toString()) //Movie(name=Whiplash, studio=Sony Pictures, rating=9.0)

    val betterRating = movie.copy(studio = "Marvel", rating = 9.5F)
    println(betterRating.toString()) // Movie(name=Whiplash, studio=Sony Pictures, rating=9.5)
    val (name, studio) = betterRating
    println("name = ${betterRating.component1()}")
    println("rating = ${betterRating.component3()}")


    val intention: Intention = Intention.LoadMore()
    val output = when (intention) {
        is Intention.Refresh -> "refresh"
        is Intention.LoadMore -> "load more"
    }
    println(output)


    val parameterizedClass = ParameterizedClass<String>("string-value")

    val res = parameterizedClass.getValue()
    println("${res is String}")

    println(Outer.Nested().b)


    // creating object of Nested class
    val nested = Outer.Nested()
    println(nested.callMe())

    val outer = Outer2()
    println("Using outer object: ${outer.Inner().callMe()}")

    val inner = Outer2().Inner()
    println("Using inner object: ${inner.callMe()}")

    val mobile1: Mobile = Mobile("IPhone", MobileColor.GOLD)
    val mobile2: Mobile = Mobile("SONY", MobileColor.BLACK)

    // access enum variables
    println("The color of my " + mobile1.name + " is " + mobile1.color)
    println("The color of my " + mobile2.name + " is " + mobile2.color)

    // access the value of the variable in Enum Object
    println(mobile1.color.toString() + " value is " + mobile1.color.value)
    println(mobile2.color.toString() + " value is " + mobile2.color.value)

    var funsample = argTest()
    funsample.logValue()

    val greeting = { println("Hello!") }

    // invoking function
    greeting()

    val product = { a: Int, b: Int -> println("${a * b}") }
    product(9, 3)

    val addLambda = { a: Int, b: Int -> accept(a, b) }
    var resultVal = addLambda(10, 40)
    println("Printed from lambda func as ${resultVal / 5}")

    val russianNames = arrayOf("Maksim", "Artem", "Sophia", "Maria", "Maksim")
            .filter { it.startsWith("m", ignoreCase = true) }
    println("names are ${russianNames.toString()}")

    val doStuff = lambda@{ stopEarly: Boolean ->
        println("line 1")
        if (stopEarly) return@lambda
        println("line 2")
    }

    doStuff(true)
    doStuff(false)

    var list = arrayListOf<Int>()
    for (number in 1..10) {
        list.add(number)
    }
    var resultList = list.filterOnCondition { isMultipleOf(it, 5) }


    val resu1t1 = operate(10, 5, ::sum)
    println("The sum of 10 and 5 is $resu1t1")

    val result2 = operate(5, 2, ::sum)
    println("The sum of 5 and 2 is $result2")

    println("Subtraction of 100 and 40 is ${operate(100, 40, ::sub)}")
    println("The product between 5 and 20 is ${operate(5, 20, 30, ::multiply)}")
    println("The division between 10 and 5 is ${operate(10, 5, ::divide)}")


    var resNew = arrayOf("arun", "mohan", null)
    for (item in resNew) {
        item?.let { resNew = resNew.plus(it); it }
                ?.also { it -> println("non nullable value: $it") }

    }
    println("resNew size ${resNew.size} and value is ${resNew}")
    var resNonNull = resNew.filterNotNull()
    println("resNew size ${resNonNull.size}  and value is ${resNonNull}")

    whenSample()

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // local return to the caller of the anonymous fun, i.e. the forEach loop
        print(value)
    })
    print(" done with anonymous function")
}

fun accept(a: Int, b: Int): Int {
    val addRes = a + b;
    println("Printed from accept func as $addRes")
    return addRes;
}

fun <T> ArrayList<T>.filterOnCondition(condition: (T) -> Boolean): ArrayList<T> {
    val result = arrayListOf<T>()
    for (item in this) {
        if (condition(item)) {
            result.add(item)
        }
    }

    return result
}

fun whenSample() {
    lateinit var exp: Any
    exp = 0
    if (exp is Int) {
        println("is int")
    }
}


fun isMultipleOf(number: Int, multipleOf: Int): Boolean {
    return number % multipleOf == 0
}

fun operate(v1: Int, v2: Int, fn: (Int, Int) -> Int): Int {
    return fn(v1, v2)
}

fun operate(v1: Int, v2: Int, v3: Int, fn: (Int, Int, Int) -> Int): Int {
    return fn(v1, v2, v3)
}

fun sum(x1: Int, x2: Int) = x1 + x2

fun sub(x1: Int, x2: Int) = x1 - x2

fun multiply(x1: Int, x2: Int, x3: Int) = x1 * x2 * x3

fun divide(x1: Int, x2: Int) = x1 / x2


data class Mobile(val name: String, val color: MobileColor)

enum class MobileColor(val value: Int) {
    GOLD(0xffd323),
    SILVER(0xeaeaea),
    WHITE(0xffffff),
    BLACK(0x000000),
    RED(0xFF0000)
}

data class Movie(var name: String, var studio: String, var rating: Float)

sealed class Intention {
    class Refresh : Intention()
    class LoadMore : Intention()
}

class ParameterizedClass<A>(private val value: A) {

    fun getValue(): A {
        return value
    }
}

class Outer {

    val a = "Outside Nested class."

    class Nested {
        val b = "Inside Nested class."
        fun callMe() = "Function call from inside Nested class."
    }
}

class Outer2 {

    val a = "Outside Nested class."

    inner class Inner {
        fun callMe() = a
    }
}

fun sampleArg(arg: String = "", arg2: Int = 0, arg3: Boolean = false) {}

class argTest {
    val argTestVal = sampleArg();
    val list = asList(1, "hey", 3)
    fun logValue() {
        println("size of list is ${list.toString()}")
    }
}

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

class Address : testInterface {
    override fun test1() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun test2() {
        super<testInterface>.test2()
    }

    var name: String = "Arunkarthick" // getter

        get() = field
    // setter
        set(value) {
            field = value + "@ibotsystems.com"
        }
    var street: String = "Hopes"
    var city: String = "Coimbatore"
    var state: String? = "Tamil Nadu"
    var zip: String = "641014"
    var salary = null
    val isNull: Boolean
        get() = this.salary == null

}


interface testInterface {
    fun test1()
    fun test2() {
        // optional body
    }
}

fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street
    return result
}

fun displayName(firstName: String, lastName: String): String = "$firstName $lastName"
fun addWithoutReturn() {
    println("value from addition without return ${add(7, 8)}")
}

fun factorial(n: Int): Long {
    return if (n == 1) n.toLong() else n * factorial(n - 1)
}

fun add(a: Int, b: Int): Int {
    return a + b;
}

fun displayBorder(character: Char = '=', length: Int = 15) {
    for (i in 1..length) {
        print(character)
    }
}

//create pyramid
class Structure() {

    infix fun createPyramid(rows: Int) {
        var k = 0
        for (i in 1..rows) {
            k = 0
            for (space in 1..rows - i) {
                print("  ")
            }
            while (k != 2 * i - 1) {
                print("* ")
                ++k
            }
            println()
        }
    }
}

class Hero {
    fun useSuperpowers() {
        println("Applied super powers")
    }

    fun addSuperPower(a: Int, b: Int) {
        println("new super powers added to character with power ${a * b}")
    }
}

fun Hero.savePlanet() {
    useSuperpowers()
    addSuperPower(5, 10)
}

fun String.add(str1: String, str2: String): String {
    return this + str1 + str2
}
