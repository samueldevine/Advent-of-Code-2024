import java.io.File

fun main() {
    val input = File("data/02.txt")
    var safe = 0

    input.forEachLine { report ->
        val strLevels: List<String> = report.split(" ")
        val levels = strLevels.map { it.toInt() }

        if (isReportSafe(levels) == -1) {
            safe ++
        } else {
            var isSafeAfterRemoval = false
            for (i in levels.indices) {
                val filteredLevels = levels.toMutableList().apply { removeAt(i) }
                if (isReportSafe(filteredLevels) == -1) {
                    isSafeAfterRemoval = true
                    break
                }
            }
            if (isSafeAfterRemoval) {
                safe++
            }
        }
    }
    println(safe)
}

fun isReportSafe(levels: List<Int>): Int {
    // return index where report was deemed unsafe, or -1 if report is safe

    val increasing: Boolean = levels[0] <= levels[1]

    for (i in levels.indices) {
        if (i == 0) continue // skip first level

        val diff = levels[i] - levels[i - 1]
        if (increasing && (diff < 1 || diff > 3)) {
            return i
        }

        if (!increasing && (diff < -3 || diff > -1)) {
            return i
        }
    }

    return -1
}