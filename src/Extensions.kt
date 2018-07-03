fun main(args: Array<String>) {
    val myString= "Hello Everyone"
    val result = myString.removeFirstLastChar()
    println("First character is: $result")

    val str1:String = "Hello"
    val str2:String = "World"
    val str3:String ="Hey"

    println("""Added strings are ${str1.add(str2,str3)}"""")
}



fun String.removeFirstLastChar(): String =  this.substring(1, this.length - 1)
