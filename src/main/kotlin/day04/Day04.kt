package day04

import java.io.File

fun main() {

    val file = File("src/main/kotlin/day04/input.txt")

    part01(file)
    part02(file)


}

fun part01(file: File) {

    val map = file.bufferedReader().readLines()

    val width = map[0].length
    val height = map.size

    var count = 0
    map.forEachIndexed { y, row ->
        row.forEachIndexed { x, c ->

            if (map[y][x] == '@') {

                val nbs = getNeighbours(x, y, width, height)
                val nbCount = nbs.count { p ->
                    map[p.second][p.first] == '@'
                }

                if (nbCount < 4) count++
            }
        }
    }

    println("solution part 01: $count")

}

fun getNeighbours(x: Int, y: Int, w: Int, h: Int): List<Pair<Int, Int>> {

    return listOf(
        Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1),
        Pair(x - 1, y), Pair(x + 1, y),
        Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1)
    ).filter {
        it.first >= 0 && it.first < w && it.second >= 0 && it.second < h
    }

}


fun part02(file: File) {

    val map = file.bufferedReader().readLines().map { line ->
        line.toCharArray()
    }

    val width = map[0].size
    val height = map.size

    var removed = 0

    val candidates = mutableListOf<Pair<Int, Int>>()
    val toRemove = mutableListOf<Pair<Int, Int>>()

    map.forEachIndexed { y, row ->
        row.forEachIndexed { x, c ->
            if (map[y][x] == '@') {
                val nbs = getNeighbours(x, y, width, height).filter { p ->
                    map[p.second][p.first] == '@'
                }
                if (nbs.size < 4) {
                    toRemove.add(Pair(x, y))
                    candidates.addAll(nbs.filter { it !in candidates })
                    removed++
                }
            }
        }
    }

    toRemove.forEach { p ->
        map[p.second][p.first] = '.'
        candidates.remove(p)
    }

    while (candidates.isNotEmpty()) {

        val p = candidates.removeLast()

        val nbs = getNeighbours(p.first, p.second, width, height)
            .filter { map[it.second][it.first] == '@' }

        if (nbs.size < 4) {
            map[p.second][p.first] = '.'
            candidates.addAll(nbs.filter { it !in candidates })
            removed++
        }

    }

    println("solution part 02: $removed")
}




