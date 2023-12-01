package day1

import readLines

fun main() = Part1().solve()

private class Part1 {
    fun solve() {
        val sum =
            readLines(javaClass.getResourceAsStream("day1/input.txt")!!)
                .filter { line -> line.any { it.isDigit() } }
                .sumOf { line ->
                    "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
                }
        println(sum)
    }
}
