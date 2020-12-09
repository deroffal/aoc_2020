import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day09Test {

    private val input = listOf("35", "20", "15", "25", "47", "40", "62", "55", "65", "95", "102", "117", "150", "182", "127", "219", "299", "277", "309", "576")
    private val day = Day09(5, input)

    @Test
    fun `part1 matches example`() {
        assertEquals(127, day.part1())
    }

    @Test
    fun `part2 matches example`() {
        assertEquals(62, day.part2(127L))
    }
}