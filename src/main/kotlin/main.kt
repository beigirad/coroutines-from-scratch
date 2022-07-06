import kotlinx.coroutines.Job
import kotlin.system.measureTimeMillis

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // this: CoroutineScope
    printThread("start")

    // launch a new coroutine and start it
    launch {
        calculateSumOf(1_000_000)
    }
    // launch a new coroutine and start it
    val job: Job = launch {
        calculateSumOf(2_000_000)
    }
    // launch a new coroutine and start it
    launch {
        calculateSumOf(3_000_000)
    }

    delay(1100)
    job.cancel()

    printThread("after")
}

suspend fun calculateSumOf(n: Int): Int {
    println(">> start calculation for $n")
    var sum = 0

    // heavy calculation
    val time = measureTimeMillis {
        sum = (0..n).fold(0) { acc: Int, i: Int -> acc + i }
        delay(n / 1_000L)
    }

    printThread("calculation of $n")
    println("<< calculation took ${time}ms. sum of 0 to $n is $sum")
    return sum
}

fun printThread(tag: String) = println("$tag ====> runs on [${Thread.currentThread().name}]")