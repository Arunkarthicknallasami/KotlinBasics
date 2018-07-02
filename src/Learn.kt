fun main(args: Array<String>) {

    var descr = describe(3.0)
    println("value is $descr")
    testInt()
    reverseList()
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    val nums = listOf(11, 5, 3, 8, 1, 9, 6, 2)

    val len = nums.count()
    val max = nums.max()
    val min = nums.min()
    val sum = nums.sum()
    val avg = nums.average()

    val msg = """
               max: $max, min: $min,
               count: $len, sum: $sum,
               average: $avg
              """
    println(msg.trimIndent())


    val sortAsc = nums.sorted()
    println(sortAsc)


    val sortDesc = nums.sortedDescending()
    println(sortDesc)

    val revNums = nums.reversed()
    println(revNums)

    val a = 50
    val b = 10

    var maxVal: Int
    maxVal = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
    maxVal = findVal(100)
    println("$maxVal")

}

fun findVal(x: Int) =
        when (x) {
            in 1..5, in 25..100 -> if (x == 1) {
                100
            } else {
                -100
            }
            else -> 0
        }


fun describe(obj: Any): String? =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

fun testInt() {
    val list = listOf("a", "b", "c", "d", "e", "f", "g","2")
    val index = list.lastIndex
    val size = list.size
    val indices = list.indices
    var number=5
    if ((list.size) - 1 in 0..list.lastIndex) {
        println("$(list.size) is in range")
    }
    if (number in list.indices) {
        println("$number is in range of list items and its value is ${list.indexOf("$number")}")
    }
}

fun reverseList() {
    val orgList = listOf("a", "b", "c", "d", "e", "f", "g")
    val templist2 = orgList.asReversed()
    println("$templist2")
    /*var count:Int = 3
    println("value incremented is ${count.plus(3)}")*/
}

