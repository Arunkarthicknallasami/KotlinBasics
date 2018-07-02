import java.awt.TextArea
import kotlin.properties.Delegates

fun main(args: Array<String>) {
    println(lazyValue)

    val student = Student()
    student.name = "first"
    student.name = "second"
    student.name = "third"

}
/**Init value later*/
lateinit var mode:TextArea

class Student {
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$old -> $new")
    }
}

val lazyValue: String by lazy {
    "Hello"
}
