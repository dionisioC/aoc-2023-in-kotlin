fun main() {
    data class Race(val time: Long, val distance: Long)

    fun possibilities(races: MutableList<Race>): Long {
        var result = 1L
        for (race in races) {
            var possibility = 0
            for (i in 0 .. race.time) {
                val possibleDistance = (race.time - i) * i
                if (possibleDistance > race.distance) {
                    possibility += 1
                }
            }
            result *= possibility
        }
        return result
    }

    fun part1(input: List<String>): Long {
        val races: MutableList<Race> = ArrayList()
        var times: List<Long> = ArrayList()
        var distance: List<Long> = ArrayList()
        for (line in input) {
            if (line.startsWith("Time: ")) {
                times = line.substringAfter("Time: ").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
            }

            if (line.startsWith("Distance: ")) {
                distance = line.substringAfter("Distance: ").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
            }
        }
        for (i in times.indices) {
            races.add(Race(times[i], distance[i]))
        }
        return possibilities(races)
    }

    fun part2(input: List<String>): Long {
        var time = 0L
        var distance = 0L
        for (line in input) {
            if (line.startsWith("Time: ")) {
                time = line.filter { it.isDigit() }.toLong()
            }

            if (line.startsWith("Distance: ")) {
                distance = line.filter { it.isDigit() }.toLong()
            }
        }

        return possibilities(mutableListOf(Race(time, distance)))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    part1(testInput).println()

    val input = readInput("Day06")
    part2(input).println()
}
