import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day06Test{

    private val day = Day06("day06.txt".splitFileToList("\r\n\r\n"))

    @Test
    fun `part1 matches example`(){
        assertEquals(11, day.part1())
    }

    @Test
    fun `part2 matches example`(){
        assertEquals(6, day.part2())
    }
}