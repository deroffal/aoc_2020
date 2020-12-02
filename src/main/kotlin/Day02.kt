fun main() {
    val day = Day02()
    val input = "day02.txt".fileToList()
    println("Part 1 : ${day.part1(input)}")
    println("Part 2 : ${day.part2(input)}")
}

class Day02 {
    val regex = "(\\d*)-(\\d*)\\s([a-z]):\\s([a-z]*)".toRegex()

    fun part1(input: List<String>) = countValidPasswords(input) {
        PasswordByOccurencyMatcher(it[1].toInt(), it[2].toInt(), it[3][0], it[4]).matches()
    }

    fun part2(input: List<String>) = countValidPasswords(input) {
        PasswordByPositionMatcher(it[1].toInt(), it[2].toInt(), it[3][0], it[4]).matches()
    }

    private fun countValidPasswords(input: List<String>, predicate: (List<String>) -> Boolean) = input
        .map { regex.find(it)!!.groupValues }
        .count(predicate)
}

class PasswordByOccurencyMatcher(val minOccurency: Int, val maxOccurency: Int, val letter: Char, val password: String) {
    fun matches() = password.count { letter == it } in (minOccurency..maxOccurency)
}

class PasswordByPositionMatcher(val firstPosition: Int, val secondPosition: Int, val letter: Char, val password: String ) {
    fun matches() = listOf(password[firstPosition - 1], password[secondPosition - 1]).count { it == letter } == 1
}
