fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            var cardPoints = 0
            var first = true
            val rawNumbers = line.substringAfter(':').trim()
            val winningNumbersAndNumbers = rawNumbers.split("|")
            val winningNumbers = winningNumbersAndNumbers[0].trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
            val numbers = winningNumbersAndNumbers[1].trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() }

            for (number in numbers) {
                if (winningNumbers.contains(number)) {
                    if (first) {
                        cardPoints = 1
                        first = false
                    } else {
                        cardPoints *= 2
                    }
                }
            }

            result += cardPoints

        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    part1(testInput).println()

    val input = readInput("Day04")
    part2(input).println()
}




