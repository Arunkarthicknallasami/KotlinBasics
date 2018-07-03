
/**
 * Function which accepts or returns lambda functions are known as high level function
 * */

fun main(args: Array<String>) {
    /**Type1 of declaration*/
    val type1 = { x: Int, y: Int -> x * y }
    println("value of type1 is ${type1(2, 5)}")

    val type2: (Int, Int) -> Int = { x, y -> x * y }
    println("value of type2 is ${type2(2, 5)}")

    val numbers = listOf(7, 5, 1, 4, -10, 58)
    val n = numbers.count { x -> x > 3 }
    println(n)
    /**above can also be written as follows with 'it' which is an default param in lambda's*/
    val y = numbers.count { it > 3 } //when there are only one argument in lambda we can remove () from function
    println(y)

    var sum = 0
    numbers.forEach { sum += it }
    println("the sum of $numbers is $sum")

    /**
     * find max of a list using lambda, if lamda is the last argument then it can be moved out of ()
     * */
    var maxValue = max(numbers) { x, y -> x < y }
    println("Maximum value from $numbers is $maxValue")

    val increaseBy = fun Int.(value: Int) = this + value
    val x = 1
    println("$x + 3 = ${x.increaseBy(3)}")


    /**invoke methods can be used in any class which can be accessed directly*/
    val a = Average()
    val avg = a(1.0, 2.0, 3.0)
    println("Average value is $avg")

    //similarly
    operator fun String.invoke():String{
        return this.toUpperCase()
    }

    val two = "two"
    println("value of two is ${two()}")

    val program = Program()
    val mylambda :(Int) -> Unit = { println(it)}
    program.addTwoNumbers(5,11,mylambda)
}

class Average {
    operator fun invoke(vararg values: Double): Double {
        var sum = 0.0
        for (x in values)
            sum += x
        return sum / values.size
    }
}
class Program{
    fun addTwoNumbers(a:Int,b:Int,action:(Int) ->Unit)
    {
        val sum =a+b
        action(sum)
    }
}

fun <T> max(collections: Collection<T>, less: (T, T) -> Boolean): T? {
    var max: T? = null
    for (x in collections)
        if (max == null || less(max, x))
            max = x
    return max
}