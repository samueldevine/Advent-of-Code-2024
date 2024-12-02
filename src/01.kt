import java.io.File
import kotlin.math.abs

fun main() {
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    val file = File("data/01.txt")

    // Populate each list with the values from each column
    file.forEachLine { line ->
        val columns = line.trim().split(Regex("\\s{1,}"))

        list1.add(columns[0].toInt())
        list2.add(columns[1].toInt())
    }

    list1.sort()
    list2.sort()

    var sumOfDistances = 0
    var similarityScore = 0

    list1.forEachIndexed { index, value ->
        sumOfDistances += abs(list1[index] - list2[index])
        similarityScore += (value * list2.count { it == value })
    }

    println("Total Distance: $sumOfDistances")
    println("Similarity Score: $similarityScore")
}