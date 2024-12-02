import java.io.File
import kotlin.math.abs

fun main() {
    var list1 = mutableListOf<Int>()
    var list2 = mutableListOf<Int>()

    val file = File("data/01.txt")

    file.forEachLine { line ->
        val columns = line.trim().split(Regex("\\s{1,}"))

        list1.add(columns[0].toInt())
        list2.add(columns[1].toInt())
    }

    list1.sort()
    list2.sort()

    var sum = 0

    list1.forEachIndexed { index, _ ->
        sum += abs(list1[index] - list2[index])
    }

    println("Total Distance: $sum")
}