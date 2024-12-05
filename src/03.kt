private const val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""

private const val doRegex = """do\(\)"""

private const val dontRegex = """don't\(\)"""



fun addValidMultiplicationsPart1(section: String): Long {
    return mulRegex.toRegex().findAll(section).sumOf {
        val (first: String, second: String) = it.destructured
        first.toLong() * second.toLong()
    }
}

fun addValidMultiplicationsPart2(lines: List<String>): Long {
    val fullRegex = """$mulRegex|$doRegex|$dontRegex|""".toRegex()
    val all : List<String> = lines.flatMap { string : String ->
        fullRegex.findAll(string).map { it.value }
    }
    println(all)
    var enabled = true
    var sum = 0L
    for (instruction : String in all) {
        when {
            instruction == "don't()" -> enabled = false
            instruction == "do()" -> enabled = true
            enabled && instruction.startsWith("mul(") -> {
                val (a : String, b : String) = instruction
                    .removeSurrounding("mul(", ")")
                    .split(',')
                sum += a.toInt() * b.toInt()
            }
        }
    }
    return sum
}

fun main() {
    fun part1(input: List<String>): Long {
        return input.sumOf { addValidMultiplicationsPart1(it) }
    }

    fun part2(input: List<String>): Long {
        return addValidMultiplicationsPart2(input)
    }

    val input = readInput("03")

    println(part1(input))
    println(part2(input))
}
