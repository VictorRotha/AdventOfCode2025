package day05

import java.io.File

data class Input(
    val ranges: List<Pair<Long, Long>>,
    val ids: List<Long>
)

fun main() {

    val input = getInput()

    part01(input)
    part02(input)

}

fun part01(input: Input) {

    val fresh = mutableSetOf<Long>()

    input.ids.forEach { id ->
        input.ranges.forEach { range ->
            if (id in range.first .. range.second) {
                fresh.add(id)
                return@forEach
            }
        }
    }

    println("Solution part 01: ${fresh.size}")
}

fun part02(input: Input) {

    val ranges = input.ranges.toMutableList()

    val processed = mutableSetOf<Pair<Long, Long>>()
    var currentRange : Pair<Long, Long>?

    while( ranges.isNotEmpty() ) {

        currentRange = ranges.removeLast()

        for (p in processed) {

            if (currentRange!!.first >= p.first && currentRange.second <= p.second) {
                currentRange = null
                break
            }

            if (currentRange.first < p.first && currentRange.second > p.second) {
                val range1 = Pair(currentRange.first, p.first - 1)
                val range2 = Pair(p.second + 1, currentRange.second)
                ranges.add(range1)
                ranges.add(range2)
                currentRange = null
                break
            }

            if (currentRange.first in p.first .. p.second ) {
                currentRange = Pair(p.second + 1, currentRange.second)

            } else if (currentRange.second in p.first .. p.second) {
                currentRange = Pair(currentRange.first, p.first - 1)
            }
        }

        currentRange?.let {
            processed.add(it)
        }
    }

    var total = 0L
    processed.forEach {
        total += it.delta()
    }

    println("Solution part 02: ${total}")
}

fun getInput() : Input {

    val file = File("src/main/kotlin/day05/input.txt")

    val ranges = mutableListOf<Pair<Long, Long>>()
    val ids = mutableListOf<Long>()

    file.bufferedReader().forEachLine { line ->

        when {
            line.contains("-") -> ranges.add(
                Pair(line.split('-')[0].toLong(), line.split('-')[1].toLong() )
            )
            line.isNotEmpty() -> ids.add(line.toLong())
        }

    }

    return Input(ranges, ids)
}

fun Pair<Long, Long>.delta() : Long {
    if (second < first)
        throw IllegalArgumentException("end of range have to be bigger or equal start")
    return second - first + 1
}
