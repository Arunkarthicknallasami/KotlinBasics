fun main(args: Array<String>) {
    /**Immutable Collections -- Immutable are items which once assigned cannot be modified and also has fixed size, Read only.
     * listOf<type> - type can be int or string or any
     * */

    var iList = listOf("arun", "karthick", "sam")
    for (element in iList.indices) {
        println("Element from iList is ${iList.get(element)}")  //or ${iList.[element]}
    }

    val iMap = mapOf(1 to "Android", 2 to "ios", 3 to "Blackberry")
    for ((key, value) in iMap) {
        println("key from iMap is $key and value is $value")
    }
    //or
    for (item in iMap) {
        println("key from iMap is ${item.key} and value is  ${item.value} ")
    }

    var iSet = setOf("arun", "arun")
    for (item in iSet) {
        println("key from iSet is $item ")
    }

    /**list samples are as follows*/
    var listMixedTypes = listOf("Chike", 1, 2.445, 's') // will still compile
    val emptyList: List<String> = emptyList() //the emptyList() Function
    val nonNullsList: List<Int> = listOfNotNull(2, 45, 2, null, 5, null)

    /**Mutable lists*/
    val stringList: ArrayList<String> = arrayListOf("Hello", "You", "There")
    val mutableListNames: MutableList<String> = mutableListOf("Josh", "Kene", "Sanya")

    /**Mutable map*/
    val mMap = mutableMapOf(1 to "Android", 2 to "ios", 3 to "Blackberry")
    mMap.put(4, "Symbian")
    mMap.replace(2, "Ios")
    mMap.remove(3)
    for ((key, value) in mMap) {
        println("key from mMap is $key and value is $value")
    }

    val hMap = hashMapOf(1 to "Android", 2 to "ios", 3 to "Blackberry")
    for ((key, value) in hMap) {
        println("key from hMap is $key and value is $value")
    }
    val mapOf = mapOf(1 to "Android", 2 to "ios", 3 to "Blackberry")
    for ((key, value) in mapOf) {
        println("key from mapOf is $key and value is $value")
    }

    /**HashSet*/
    var hSet = hashSetOf("arun", "arun")
    for (item in hSet) {
        println("key from hSet is $item ")
    }


    /**Filter functions  -- more functions can be found at https://antonioleiva.com/collection-operations-kotlinF*/
    var intArray = listOf(1, 5, 89, 782, 445, 4, 6, 77, 99, 7, 32, 977)
    intArray.filter { it < 100 }
            .sortedBy { it }
            .forEach { println(it) }

    var people = listOf<Human>(Human("Sam", 25), Human("Peter", 54), Human("Henry", 48))
    var peopleNames = people.map { it.name }
    for (people in peopleNames)
    {
        println(people)
    }
}