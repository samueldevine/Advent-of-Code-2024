private const val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""

private const val doRegex = """do\(\)"""

private const val dontRegex = """don't\(\)"""



fun addValidMultiplicationsPart1(section: String): Long {
    return mulRegex.toRegex().findAll(section).sumOf {
        val (first: String, second: String) = it.destructured
        first.toLong() * second.toLong()
    }
}

fun addValidMultiplicationsPart2(fullMemory: String): Long {
    var sum = 0L
    var enabled = true
    """$mulRegex|$doRegex|$dontRegex|""".toRegex().findAll(fullMemory).forEach { match : MatchResult ->
        when (match.value) {
            "don't()" -> enabled = false
            "do()" -> enabled = true
            else -> if (enabled) sum += match.multiplyNumbers()
        }
    }
    return sum
}

private fun MatchResult.multiplyNumbers(): Long {
    val (first : String, second : String) = destructured
    return first.toLong() * second.toLong()

}

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { addValidMultiplicationsPart1(it) }
    }

    fun part2(input: List<String>): Long {
        return addValidMultiplicationsPart2(input.joinToString())
    }

    val input = readInput("03")
    println(part1(input))
    println(part2(input))
}
