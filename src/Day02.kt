fun main() {
    fun part1(input: List<String>): Int {
        val maxCubes = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        var result = 0

        for (line in input) {
            val id = line.substringBefore(':').substringAfter("Game ").toInt()
            val sets = line.substringAfter(':').split(";")
            var possible = false
            for (set in sets) {
                val cubes = set.split(",")
                for (cube in cubes) {
                    val numberCube = cube.trim().split(" ")
                    if (maxCubes.getOrDefault(numberCube[1], Int.MAX_VALUE) < numberCube[0].toInt()) {
                        possible = false
                        break
                    } else {
                        possible = true
                    }
                }
                if (!possible) {
                    break
                }
            }
            if (possible) {
                result += id
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        for (line in input) {
            val minimumCubes = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )
            val sets = line.substringAfter(':').split(";")
            for (set in sets) {
                val cubes = set.split(",")
                for (cube in cubes) {
                    val numberCube = cube.trim().split(" ")
                    if (minimumCubes.getOrDefault(numberCube[1], Int.MAX_VALUE) < numberCube[0].toInt()) {
                        minimumCubes[numberCube[1]] = numberCube[0].toInt()
                    }
                }
            }

            var power = 1
            for ((_, v) in minimumCubes) {
                power *= v
            }

            result += power
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    part1(testInput).println()

    val input = readInput("Day02")
    part2(input).println()
}
