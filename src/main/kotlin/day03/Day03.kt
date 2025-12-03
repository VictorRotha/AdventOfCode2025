package day03

import java.io.File

fun main() {

    val file = File("src/main/kotlin/day03/input.txt")

    val sum1 = findSum(file, 2)
    val sum2 = findSum(file, 12)

    println("solution part 01: $sum1")
    println("solution part 02: $sum2")

}

fun findSum(file: File, digits: Int) : Long {

    var sum = 0L

    file.bufferedReader()
        .forEachLine { line ->

            val ids = MutableList(digits) {0}
            var maxN : Int

            for (pos in 0 ..< digits) {
                val startIdx = if (pos > 0) ids[pos - 1] + 1 else 0
                maxN = line[startIdx].toString().toInt()
                ids[pos] = startIdx

                for (i in startIdx..< line.length - (digits - pos - 1)) {
                    val n = line[i].toString().toInt()
                    if (n > maxN) {
                        maxN = n
                        ids[pos] = i
                    }
                    if (maxN == 9)
                        break
                }
            }

            val joltage = ids.map { id -> line[id]}.joinToString(separator = "") { it.toString() }.toLong()
            sum += joltage

        }

    return sum
}