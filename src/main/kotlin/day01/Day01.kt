package day01

import java.io.File
import kotlin.math.abs

fun main() {

    val file = File("src/main/kotlin/day01/input.txt")

    part01(file)
    part02(file)
}

fun part01(file: File, start: Int = 50) {

    var pos = start
    var count = 0

    file.bufferedReader()
        .readLines()
        .forEach {
            val n = it.substring(1)
            pos = if (it.startsWith("R"))
                (pos + n.toInt()).mod(100)
            else {
                (pos - n.toInt()).mod(100)
            }

            if (pos == 0)
                count++
        }

    println("solution part 1: $count")

}

fun part02(file: File, start: Int = 50) {

    var pos = start
    var count = 0

    file.bufferedReader()
        .readLines()
        .forEach {

            val n = it.substring(1).toInt()
            val old = pos

            pos = if (it.startsWith("R"))
                (pos + n)
             else
                (pos - n)

            count += abs(pos / 100)
            if (pos <= 0 && old > 0) count ++

            pos = pos.mod(100)

        }

    println("solution part 2: $count")

}