package day2

import readLines

fun main(args: Array<String>) = Part1().solve(args[0])

private class Part1 {
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
            games.filter { game ->
                game.cubes.all {
                    (it[Cube.RED] ?: 0) < 13 && (it[Cube.GREEN] ?: 0) < 14 && (
                        it[Cube.BLUE]
                            ?: 0
                    ) < 15
                }
            }.sumOf { it.id }
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
