package day1

import readLines

fun main() = Part2().solve()

private class Part2 {
    fun solve() {
        val sum = readLines(javaClass.getResourceAsStream("day1/input.txt")!!).sumOf { countLine(it) }
        println(sum)
    }

    private val digits =
        mapOf(
            Pair("0", 0),
            Pair("1", 1),
            Pair("2", 2),
            Pair("3", 3),
            Pair("4", 4),
            Pair("5", 5),
            Pair("6", 6),
            Pair("7", 7),
            Pair("8", 8),
            Pair("9", 9),
            Pair("one", 1),
            Pair("two", 2),
            Pair("three", 3),
            Pair("four", 4),
            Pair("five", 5),
            Pair("six", 6),
            Pair("seven", 7),
            Pair("eight", 8),
            Pair("nine", 9),
        )

    private fun countLine(line: String): Int {
        val min =
            digits.keys
                .filter { line.contains(it) }
                .minByOrNull { line.indexOf(it) }
                ?.let {
                    digits[it]
                }
        val max =
            digits.keys
                .filter { line.contains(it) }
                .maxByOrNull { line.lastIndexOf(it) }
                ?.let {
                    digits[it]
                }

        return min?.let { "$it$max".toInt() } ?: 0
    }
}
