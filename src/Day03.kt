data class Point(val x: Int, val y: Int)

data class NumberAndPos(val number: Number, val adjacent: Set<Point>)

fun getAdjacent(point: Point): Set<Point> {
    val adjacent: MutableSet<Point> = HashSet()

    adjacent.add(Point(point.x - 1, point.y))
    adjacent.add(Point(point.x + 1, point.y))
    adjacent.add(Point(point.x, point.y - 1))
    adjacent.add(Point(point.x, point.y + 1))
    adjacent.add(Point(point.x - 1, point.y - 1))
    adjacent.add(Point(point.x + 1, point.y - 1))
    adjacent.add(Point(point.x - 1, point.y + 1))
    adjacent.add(Point(point.x + 1, point.y + 1))
    return adjacent
}

fun main() {
    fun part1(input: List<String>): Int {
        val symbols: MutableSet<Point> = HashSet()
        val numberAndPositions: MutableList<NumberAndPos> = ArrayList()
        for (line in input.withIndex()) {
            var number = ""
            var numberPositions: MutableSet<Point> = HashSet()
            for (character in line.value.withIndex()) {
                if (character.value == '.') {
                    if (number.isNotEmpty()) {
                        numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = HashSet()
                    }
                    continue
                } else if (character.value.isDigit()) {
                    number += character.value
                    numberPositions.addAll(getAdjacent(Point(line.index, character.index)))
                } else {
                    if (number.isNotEmpty()) {
                        numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = HashSet()
                    }
                    symbols.add(Point(line.index, character.index))
                }
            }

            if (number.isNotEmpty()) {
                numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
            }
        }

        var result = 0

        for (numberAndPosition in numberAndPositions) {
            for (symbol in symbols) {
                if (numberAndPosition.adjacent.contains(symbol)) {
                    result += numberAndPosition.number.toInt()
                    break
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val symbols: MutableSet<Point> = HashSet()
        val gears: MutableSet<Point> = HashSet()
        val numberAndPositions: MutableList<NumberAndPos> = ArrayList()
        for (line in input.withIndex()) {
            var number = ""
            var numberPositions: MutableSet<Point> = HashSet()
            for (character in line.value.withIndex()) {
                if (character.value == '.') {
                    if (number.isNotEmpty()) {
                        numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = HashSet()
                    }
                    continue
                } else if (character.value.isDigit()) {
                    number += character.value
                    numberPositions.addAll(getAdjacent(Point(line.index, character.index)))
                } else if (character.value == '*') {
                    if (number.isNotEmpty()) {
                        numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = HashSet()
                    }
                    gears.add(Point(line.index, character.index))
                } else {
                    if (number.isNotEmpty()) {
                        numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
                        number = ""
                        numberPositions = HashSet()
                    }
                    symbols.add(Point(line.index, character.index))
                }
            }

            if (number.isNotEmpty()) {
                numberAndPositions.add(NumberAndPos(number.toInt(), numberPositions))
            }
        }

        var result = 0
        val adjacentGears: MutableMap<Point, MutableList<Int>> = HashMap()

        for (numberAndPosition in numberAndPositions) {
            for (gear in gears) {
                if (numberAndPosition.adjacent.contains(gear)) {
                    if (adjacentGears.containsKey(gear)) {
                        adjacentGears[gear]?.add(numberAndPosition.number.toInt())
                    } else {
                        adjacentGears.put(gear, mutableListOf(numberAndPosition.number.toInt()))
                    }
                    break
                }
            }
        }


        for ((_, v) in adjacentGears) {
            if (v.size == 2) {
                result += v[0] * v[1]
            }
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    part1(testInput).println()

    val input = readInput("Day03")
    part2(input).println()
}




