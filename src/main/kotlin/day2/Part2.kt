package day2

import readLines

fun main(args: Array<String>) = Part2().solve(args[0])

private class Part2 {
    data class Game(val id: Int, val cubes: List<Map<Cube, Int>>)

    enum class Cube {
        BLUE,
        GREEN,
        RED,
    }

    fun solve(filename: String) {
        val games: List<Game> =
            readLines(javaClass.getResourceAsStream(filename)!!)
                .filter { it.isNotEmpty() }
                .map { parseLine(it) }

        val sum =
            games.sumOf { game ->
                val minBlue = game.cubes.maxOf { it[Cube.BLUE] ?: 0 }
                val minGreen = game.cubes.maxOf { it[Cube.GREEN] ?: 0 }
                val minRed = game.cubes.maxOf { it[Cube.RED] ?: 0 }
                minBlue * minGreen * minRed
            }
        println(sum)
    }

    private fun parseLine(line: String): Game {
        val matchLine = "Game (\\d+): (.*?)".toRegex().matchEntire(line)!!.groupValues
        val gameId = matchLine[1].toInt()

        val cubes =
            matchLine[2].split("; ")
                .map { cubeSet ->
                    cubeSet
                        .split(", ")
                        .associate { cubeCount: String ->
                            val countColour: List<String> =
                                "(\\d+) (.*?)".toRegex().matchEntire(cubeCount)!!.groupValues
                            Pair(Cube.valueOf(countColour[2].uppercase()), countColour[1].toInt())
                        }
                }

        return Game(gameId, cubes)
    }
}
