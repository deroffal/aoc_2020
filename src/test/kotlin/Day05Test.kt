import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {

    private val day = Day05(listOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"))

    @Test
    fun `upperHalf returns the upper half part of a list`() {
        val columns = (0..127).toMutableList()
        columns.upperHalf()
        assertEquals((64..127).toList(), columns)

        val rows = (0..7).toMutableList()
        rows.upperHalf()
        assertEquals((4..7).toList(), rows)
    }

    @Test
    fun `lowerHalf returns the lower half part of a list`() {
        val columns = (0..127).toMutableList()
        columns.lowerHalf()
        assertEquals((0..63).toList(), columns)

        val rows = (0..7).toMutableList()
        rows.lowerHalf()
        assertEquals((0..3).toList(), rows)
    }

    @Test
    fun `findRow for FBFBBFFRLR returns row 44`() {
        assertEquals(44, day.findRow("FBFBBFFRLR"))
    }

    @Test
    fun `findColumn for FBFBBFFRLR returns row 5`() {
        assertEquals(5, day.findColumn("FBFBBFFRLR"))
    }

    @Test
    fun `part1 matches example`() {
        assertEquals(820, day.part1())
    }
}