fun main() {
    val day = Day06("day06.txt".splitFileToList("\r\n\r\n"))
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day06(private val input: List<String>) {
    fun part1() = input
        .map { groupAnwsers ->
            groupAnwsers.split("").toSet().filterNot { it.isBlank() }
        }
        .sumBy { it.size }

    fun part2() = input.sumBy { group ->
        val groupAnswers = group.split("\r\n")
            .filter { it.isNotBlank() }     //or remove input's last line...

        groupAnswers.first().count { char -> groupAnswers.all { answers -> char in answers } }
    }
}
