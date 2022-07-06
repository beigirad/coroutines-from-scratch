import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    printThread("before")

    calculateSumOf(1_000_000)

    printThread("after")
}

fun calculateSumOf(n: Int): Int {
    println(">> start calculation for $n")
    var sum = 0

    // heavy calculation
    val time = measureTimeMillis {
        sum = (0..n).fold(0) { acc: Int, i: Int -> acc + i }
        Thread.sleep(n / 1_000L)
    }

    printThread("calculation")
    println("<< calculation took ${time}ms. sum of 0 to $n is $sum")
    return sum
}

fun printThread(tag: String) = println("$tag ====> runs on [${Thread.currentThread().name}]")