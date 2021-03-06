import java.math.BigInteger

fun main(args: Array<String>) {
    val result = double(2)

    /**send values only for params with no default value*/
    reformat("")
    /**send values for params only if needed*/
    reformat("", true, true, false, '_')
    /**send values for params by name*/
    reformat("", upperCaseFirstLetter = true, wordSeparator = 'g')


    val a = true
    val b = false
    var findResult: Boolean

    findResult = a or b // a.or(b)
    println("findResult = $findResult")

    findResult = a and b // a.and(b)
    println("findResult = $findResult")

    infixClass().createPyramid(5)

    println()

    /**above can also be called as */
    var infi = infixClass()
    infi createPyramid 5


    /** tail recursive function*/
    val n = 100
    val first = BigInteger("0")
    val second = BigInteger("1")

    println(fibonacci(n, first, second))


    printCircumferenceAndArea(3.0) // The circle circumference of 3.0 radius is 18.85 and area is 28.27


    normalFunction()

}
/**
 * tail recursive function.A recursive function is eligible for tail recursion if the function call to itself is the last operation it performs. */
tailrec fun fibonacci(n: Int, a: BigInteger, b: BigInteger): BigInteger {
    return if (n == 0) a else fibonacci(n-1, b, a+b)
}

/**infix class samples*/
class infixClass {
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

    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    fun range() {
        for (i in 1 until 10) {    // Same as - for(i in 1.until(10))
            print("$i ")
        }
        for (i in 10 downTo 1) {     // Same as - for(i in 10.downTo(1))
            print("$i ")
        }
        for (i in 1 until 10 step 2) { // Same as - for(i in 1.until(10).step(2))
            print("$i ")
        }
        for (i in 1..100) {
            print("$i ")
        }
        val regex = Regex("[tT]rue|[yY]es")
        val str = "yes"

        str.matches(regex)

// Infix notation of the above function call -
        str matches regex

        val c1 = ComplexNumber(3.0, 5.0)
        val c2 = ComplexNumber(4.0, 7.0)

// Usual call
        c1.add(c2) // produces - ComplexNumber(realPart=7.0, imaginaryPart=12.0)

// Infix call
        c1 add c2
    }

}

data class ComplexNumber(val realPart: Double, val imaginaryPart: Double) {
    // Infix function for adding two complex numbers
    infix fun add(c: ComplexNumber): ComplexNumber {
        return ComplexNumber(realPart + c.realPart, imaginaryPart + c.imaginaryPart)
    }
}

fun double(x: Int): Int {
    return 2 * x
}

fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {
}

/** unit return type which can be defined if needed else can be left blank*/
fun printHello(name: String?): Unit {
    if (name != null)
        println("Hello ${name}")
    else
        println("Hi there!")
    // `return Unit` or `return` is optional
}


/**
 * Inner functions
 *  The nested functions can be called only from within the enclosing function and not outside.
 *  Again, the use of nested functions makes our program more modular and tidy.
 * */
fun printCircumferenceAndArea(radius: Double): Unit {

    fun calCircumference(radius: Double): Double = (2 * Math.PI) * radius
    val circumference = "%.2f".format(calCircumference(radius))

    fun calArea(radius: Double): Double = (Math.PI) * Math.pow(radius, 2.0)
    val area = "%.2f".format(calArea(radius))

    print("The circle circumference of $radius radius is $circumference and area is $area")
}

/**Inline functions*/
fun normalFunction() {
    println("This is normal function.")
    inlineFunctionExample({ println("Inlined Functions")},{ println("Instead of object creation it copies the code.")} )
}

inline fun inlineFunctionExample(myFunction: () -> Unit, another: () -> Unit  ) {
    myFunction()
    another()
    print("Finally it's working fine!")
}