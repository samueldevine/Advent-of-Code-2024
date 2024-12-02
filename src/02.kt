import java.io.File

fun main() {
    val input = File("data/02.txt")
    var safe = 0

    var lineNumber = 0
    input.forEachLine { report ->
        lineNumber++
        var reportSafe = true
        val strLevels: List<String> = report.split(" ")
        val levels = strLevels.map { it.toInt() }
        val increasing: Boolean = levels[0] <= levels[1]

        for (i in levels.indices) {
            if (i == 0) continue // skip first level

            val diff = levels[i] - levels[i - 1]
            if (increasing && (diff < 1 || diff > 3)) {
                reportSafe = false
                break
            }

            if (!increasing && (diff < -3 || diff > -1)) {
                reportSafe = false
                break
            }
        }
        if (reportSafe) { safe += 1 }
    }
    println(safe)
}