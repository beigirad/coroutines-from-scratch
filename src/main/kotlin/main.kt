import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    printThread("before")

    val task = Runnable { calculateSumOf(1_000_000) }
    val task2 = Runnable { calculateSumOf(2_000_000) }
    Thread(task).start()
    Thread(task2).start()

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

    printThread("calculation of $n")
    println("<< calculation took ${time}ms. sum of 0 to $n is $sum")
    return sum
}

fun printThread(tag: String) = println("$tag ====> runs on [${Thread.currentThread().name}]")