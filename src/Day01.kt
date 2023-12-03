fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (calibrationLine in input) {
            val readValues = Array(2) { 'a' }
            var first = true
            for (calibrationValue in calibrationLine) {
                if (calibrationValue.isDigit()) {
                    if (first) {
                        first = false
                        readValues[0] = calibrationValue
                    } else {
                        readValues[1] = calibrationValue
                    }
                }
            }
            result += if (readValues[1].isDigit()) {
                (readValues[0].toString() + readValues[1].toString()).toInt()
            } else {
                (readValues[0].toString() + readValues[0].toString()).toInt()
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val numbers = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        var digit = 0
        for (calibrationLine in input) {
            var replacedLine = ""

            for (n in calibrationLine.indices) {
                if (calibrationLine[n].isDigit()) {
                    replacedLine += calibrationLine[n]
                } else {
                    for ((k, v) in numbers) {
                        if (calibrationLine.substring(n).startsWith(k)) {
                            replacedLine += v
                        }
                    }
                }
            }

            val digits = replacedLine.filter { it.isDigit() }
            digit += (digits.first().toString() + digits.last()).toInt()
        }
        return digit
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    part1(testInput).println()

    val input = readInput("Day01")
    part2(input).println()
}
