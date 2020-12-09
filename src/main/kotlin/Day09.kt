fun main() {
    val day = Day09(25, "day09.txt".fileToList())
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day09(private val preambuleSize: Int, private val input: List<String>) {

    val numbers: List<Long> = input.map { it.toLong() }


    fun part1() = numbers
        .takeLast(numbers.size - preambuleSize)
        .filterIndexed { idx, number -> isNotValid(number, numbers.subList(idx, idx + preambuleSize)) }
        .first()

    private fun isNotValid(number: Long, availableNumber: List<Long>): Boolean {
        return availableNumber.none { x -> availableNumber.filterNot { it == x }.any { y -> x + y == number } }
    }

    fun part2(invalidNumber: Long = 2089807806L) = numbers.indices
        .map { numberIndex -> numberIndex to sumUntilInvalidNumber(numberIndex, invalidNumber) }
        .first { it.second.second == invalidNumber }
        .run {
            numbers.subList(this.first, this.second.first).sorted()
                .run { this.first() + this.last() }
        }

    private fun sumUntilInvalidNumber(index: Int, max: Long): Pair<Int, Long> {
        var sum = 0L
        var i = index
        do {
            val l = numbers[i]
            sum += l
            i++
        } while (sum < max && i < numbers.size)
        return i to sum
    }
}