fun main(args: Array<String>) {
    //normal data class
    val jack = User("jack", 29)
    println("name = ${jack.name}")
    println("age = ${jack.age}")

    //destructuring a data class
    val (name, age) = jack
    println("name = $name")
    println("age = $age")

    //copy from data class
    // using copy function to create an object
    val daniel = jack.copy(name = "Randy")

    println("jack: name = ${jack.name}, name = ${jack.age}")
    println("u2: name = ${daniel.name}, name = ${daniel.age}")

    //tostring function
    val converterd = jack.toString()


    //data class with default values
    val defaultClass = dataClass()
    defaultClass.description = "Hey there"
    println("$defaultClass")


    //data class copy and values check
    val u1 = User("John", 29)
    val u2 = u1.copy()
    val u3 = u1.copy(name = "Amanda")

    println("u1 hashcode = ${u1.hashCode()}")
    println("u2 hashcode = ${u2.hashCode()}")
    println("u3 hashcode = ${u3.hashCode()}")

    if (u1.equals(u2) == true)
        println("u1 is equal to u2.")
    else
        println("u1 is not equal to u2.")

    if (u1.equals(u3) == true)
        println("u1 is equal to u3.")
    else
        println("u1 is not equal to u3.")
}

data class User(val name: String, val age: Int)

//set default values so that we dont have to supply all the values when initialising
data class dataClass(
        var updated_on: String = "",
        var tags: List<String> = emptyList(),
        var description: String = "",
        var user_id: List<Int> = emptyList(),
        var status_id: Int = -1,
        var title: String = "",
        var created_at: String = "",
        var data: HashMap<*, *> = hashMapOf<Any, Any>(),
        var id: Int = -1
)