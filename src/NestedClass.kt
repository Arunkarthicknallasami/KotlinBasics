fun main(args: Array<String>) {
    // accessing member of Nested class
    println(OuterClass().Nested().b)

    // creating object of Nested class
    val nested = OuterClass().Nested()
    println(nested.callMe())

    /**since it is not an class of type inner we can access it as follows*/
    val nonInner = OuterClass.nonInner()
}

/**Major diff between inner class and nested class is we need inner keyword
 * on nested class to access members of parent class. Inner classes carry a reference to an object of an outer class.*/
class OuterClass {

    val foo = "Outside Nested class."

    inner class Nested {
        val b = "Inside Nested class."
        /**foo property of Outer class from inside Nested class can be accessed only when it is internal*/
        fun callMe() = foo
    }

    class nonInner {}
}