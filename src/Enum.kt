fun main(args: Array<String>) {

    val direction = Direction.NORTH
    println("$direction")


    println("Day ${DayOfWeek.MONDAY.dayNumber} of the week is ${DayOfWeek.MONDAY}")

    //enum inside enum
    println(Months.Day.Tuesday)

    /**
     * values() returns the enum constants in the form of an array over which we can iterate to retrieve each enum constant.valueOf() is used to fetch an enum constant using a String as the argument.
    If the value doesn’t match with any of the constants, a runtime exception would be thrown.
     * */

    println(Months.January) //prints January
    println(Months.values().size) //prints 3
    println(Months.valueOf("March")) //prints March
    var x = Months.January
    println(x.shorthand) //prints JAN
    /**
     * Every enum constant has properties: name, ordinal to retrieve the name and position of the constant.
     * */
    for (enum in Months.values()) {
        println("value of enum ${enum.name} is at position ${enum.ordinal} ")
    }

    /**Enum in when*/
    var m = Months.February
    when (m) {
        Months.January  -> print("Matches with Jan")
        Months.February -> print("Matches with Feb") //prints this.
        Months.March -> print("Matches with Mar")
    }

}

enum class DayOfWeek(val dayNumber: Int) {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4),
    FRIDAY(5), SATURDAY(6), SUNDAY(7)
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Months(var shorthand: String) {
    January("JAN"),
    February("FEB"),
    March("MAR");

    /** it is optional to override tostring so that we can customise as required*/
    override fun toString(): String {
        return super.toString().toUpperCase()
    }
    enum class Day{
        Monday,
        Tuesday,
        Wednesday
    }

}