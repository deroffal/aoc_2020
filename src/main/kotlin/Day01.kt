fun main() {
    val day = Day01()
    val input = "day01.txt".fileToList().map { it.toInt() }
    println("Part 1 : ${day.part1(input)}")
    println("Part 2 : ${day.part2(input)}")
}

class Day01 {
    fun part1(expenses: List<Int>) =
        expenses.cartesianProduct()
            .first { it.first + it.second == 2020 }
            .run { this.first * this.second }

    fun part2(expenses: List<Int>) = expenses.cartesianProduct()
        .filterNot { it.first + it.second >= 2020 } // with ~ 13 ms, without ~ 300 ms
        .cartesianProduct(expenses)
        .first { it.first.first + it.first.second + it.second == 2020 }
        .run { this.first.first * this.first.second * this.second }
}

