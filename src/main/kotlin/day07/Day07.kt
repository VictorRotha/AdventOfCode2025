package day07

import java.io.File

fun main() {
    val input = getInput()

    part01(input)
    part02(input)
}

fun part02(input: List<String>) {

    val start = input[0].indexOfFirst { it == 'S' }
    var beams = listOf(Beam(start, 0))

    (0..< input.size - 1).forEach { _ ->

        beams = beams.fold(emptyList()) { acc, b ->

            val nextBeam = b.copy(y = b.y + 1)

            if (input[nextBeam.y][nextBeam.x] == '.')
                acc.toMutableList().also { it.add(nextBeam) }
            else {
                mergeBeams(acc, listOf(nextBeam.copy(x = nextBeam.x - 1), nextBeam.copy(x = nextBeam.x + 1)))
            }
        }
    }

    println("Solution part 02: ${beams.sumOf { it.timeline }}")
}

fun mergeBeams(beams1: List<Beam>, beams2: List<Beam>) : List<Beam> {

    val next = beams1.toMutableList()
    for (b in beams2) {

        val elem = next.firstOrNull { it.x == b.x && it.y == b.y }
        elem?.let {
            next.remove(elem)
            next.add(it.copy(timeline = elem.timeline + b.timeline))
        } ?: run {
            next.add(b)
        }

    }
    return next
}

data class Beam(
    val x: Int,
    val y: Int,
    val timeline: Long = 1
)

fun part01(input: List<String>) {

    val start = input[0].indexOfFirst { it == 'S' }
    var beams = setOf(Pair(start, 0))

    var count = 0

    (0..< input.size - 1).forEach { _ ->

        beams = beams.fold(mutableSetOf()) { acc, pair ->

            val nextBeam = Pair(pair.first, pair.second + 1)
            if (input[nextBeam.second][nextBeam.first] == '.')
                acc.add(nextBeam)
            else {
                acc.add(Pair(nextBeam.first - 1, nextBeam.second))
                acc.add(Pair(nextBeam.first + 1, nextBeam.second))
                count ++
            }
            acc
        }
    }

    println("Solution part 01: $count")
}

fun getInput(): List<String> {
    val file = File("src/main/kotlin/day07/input.txt")
    return file.bufferedReader().readLines()
}
