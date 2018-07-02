fun main(args: Array<String>) {
    test()
    foo()

}
/***********************************************************/

fun test(i: Int = 5, j: Int = 10) {
    loop@ for (i in 1..100) {
        ifStatement@ for (j in 1..100) {
             if (j == 10) {
                println("End reached at $j")
                break@loop
            }

        }
    }
}
/***********************************************************/
fun foo() {
    var listVal = listOf(1, 2, 3, 4, 5)
    listVal.forEach lit@ {
        if (it == 3) return@lit  // non-local return directly to the caller of foo()
        print(it)
    }
    println("this point is unreachable")
}

/***********************************************************/