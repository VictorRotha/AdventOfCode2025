package day06

import java.io.File

fun main() {

    val file = File("src/main/kotlin/day06/input.txt")

    part01(file)
    part02(file)
}

fun part02(file: File) {

    val input = file.bufferedReader().readLines()
    val maxLength = input.maxBy { it.length }.length

    val segment = mutableListOf<String>()
    var op = ' '

    var sum = 0L

    (0 .. maxLength).forEach { i ->

        var numberString = ""

        for (line in input.subList(0, input.size - 1)) {
            if (i < line.length && line[i].isDigit())
                numberString += line[i]
        }

        if ((i < input.last().length) && input.last()[i] != ' ') {
            op = input.last()[i]
        }

        if (numberString.isNotEmpty())
            segment.add(numberString)
        else {
            sum += segment.calculate(op.toString())
            segment.clear()
        }
    }

    println("Solution part 02: $sum")
}


fun part01(file: File) {

    val input = file.bufferedReader().readLines().map{
        it.split(" ").filter { it.isNotEmpty() }
    }

    val l = input[0].size

    var total = 0L

    for (i in 0 ..< l) {
        val op = input.last()[i]
        total += input.subList(0, input.size - 1)
            .map {it -> it[i]}
            .calculate(op)
    }

    println("Solution part 01: $total")
}

fun List<String>.calculate(op: String) : Long {
    return when (op) {
        "+" ->
            this.fold(0L) { acc, s -> acc + s.toLong() }

        "*" ->
            this.fold(1L) { acc, s -> acc * s.toLong() }

        else -> -1L
    }
}
