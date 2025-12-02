package day02

import java.io.File

fun main() {


    val file = File("src/main/kotlin/day02/input.txt")

    part01(file)
    part02(file)

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

fun part02(file: File) {

    val input = file.bufferedReader().readLine().trim().split(",")

    var sum = 0L
    input.forEach {
            sum += findIds(it)
    }

    println("solution part 02: $sum")

}

fun findIds(input: String) : Long {

    val end  = input.split("-")[1].toLong()
    val start = input.split("-")[0].toLong()

    var sum = 0L

    for (i in start .. end) {

        for (n in 1 .. i.toString().length / 2) {

            val pattern = i.toString().substring(0, n)

            var id = ""
            while (id.length < i.toString().length )
                id += pattern

            if (id.toLong() == i) {
                sum += i
                break
            }
        }
    }
    return sum


}