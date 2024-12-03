import java.io.File

fun main() {
    val input = File("data/03.txt")
    var result = 0

    val regex = Regex("""mul\(\d+,\d+\)""")

    input.forEachLine { line ->
        val matches = regex.findAll(line)
        val charsToFilter = listOf<Char>('m', 'u', 'l', '(', ')')
        matches.forEach { matchResult ->
            val factors: List<Int> = matchResult.groupValues[0]
                .filterNot { charsToFilter.contains(it) }
                .split(",")
                .map(String::toInt)

            val product = factors[0] * factors[1]
            result += product
        }
    }

    println(result)
}