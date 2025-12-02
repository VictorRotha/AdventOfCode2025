package day02

import java.io.File

fun main() {


    val file = File("src/main/kotlin/day02/input.txt")

    part01(file)

}

fun part01(file: File) {

    val input = file.bufferedReader().readLine().trim().split(",")

    var sum = 0L

    input.forEach {

        val end  = it.split("-")[1].toLong()
        val start = it.split("-")[0]

        var n = if (start.length > 1) start.substring(0, start.length/2).toLong()
                else start.toLong()
        var id = (n.toString() + n.toString()).toLong()

        while (id <= end) {

            if (id >= start.toLong()) {
                sum += id
            }
            n++
            id = (n.toString() + n.toString()).toLong()

        }
    }

    println("solution part 01: $sum")

}