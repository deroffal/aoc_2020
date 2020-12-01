import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day01KtTest {

    private val day = Day01()

    @Test
    fun `part1 matches example`() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        assertEquals(514579, day.part1(input))
    }

    @Test
    fun `part2 matches example`() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        assertEquals(241861950, day.part2(input))
    }
}