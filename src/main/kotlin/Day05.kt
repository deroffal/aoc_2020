fun main() {

    val day = Day05("day05.txt".fileToList())
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day05(private val input: List<String>) {

    val rows = (0..127).toList()
    val columns = (0..7).toList()
    private val allSeats = rows.cartesianProduct(columns)

    private val seats = input.map { toSeat(it) }
    private val seatIds = seats.map { toSeatId(it) }

    fun findRow(characters: String): Int {
        val r = (0..127).toMutableList()
        characters.take(7).forEach {
            if (it == 'F') {
                r.lowerHalf()
            } else {
                r.upperHalf()
            }
        }
        return r.first()
    }

    fun findColumn(characters: String): Int {
        val c = (0..7).toMutableList()
        characters.takeLast(3).forEach {
             if (it == 'L') {
                c.lowerHalf()
            } else {
                c.upperHalf()
            }
        }
        return c.first()
    }

    fun part1() = input
        .map { toSeat(it) }
        .map { toSeatId(it) }
        .maxOrNull()

    private fun toSeat(characters: String) = findRow(characters) to findColumn(characters)
    private fun toSeatId(seat: Pair<Int, Int>): Int = 8 * seat.first + seat.second

    fun part2() = allSeats
        .filterNot { it in seats }
        .find {
            toSeatId(it).run { this + 1 in seatIds && this - 1 in seatIds }
        }?.run { toSeatId(this) }

}

fun <T> MutableList<T>.lowerHalf() = removeAll(takeLast(this.size / 2))
fun <T> MutableList<T>.upperHalf() = removeAll(take(this.size / 2))