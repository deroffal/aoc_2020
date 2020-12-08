import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day08Test {

    private val day = Day08(listOf("nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1", "jmp -4", "acc +6"))

    @BeforeEach
    fun setup() {
        day.accumulator = 0
    }

    @Test
    fun `parseInput returns a map of (position, Int -  Pair(Instruction, Argument))`() {
        val map = day.orderedInstructions

        assertEquals(9, map.size)

        assertAll(
            { assertEquals("nop" to 0, map[0]) },
            { assertEquals("acc" to 1, map[1]) },
            { assertEquals("jmp" to 4, map[2]) },
            { assertEquals("acc" to 3, map[3]) },
            { assertEquals("jmp" to -3, map[4]) },
            { assertEquals("acc" to -99, map[5]) },
            { assertEquals("acc" to 1, map[6]) },
            { assertEquals("jmp" to -4, map[7]) },
            { assertEquals("acc" to 6, map[8]) }
        )
    }

    @Test
    fun `getNextInstructionPosition returns the next instructions according to the operation`() {
        assertEquals(1, day.getNextInstructionPosition(0))
        assertAll(
            { assertEquals(2, day.getNextInstructionPosition(1)) },
            { assertEquals(1, day.accumulator) }
        )
        assertEquals(6, day.getNextInstructionPosition(2))
    }

    @Test
    fun `part1 matches example`() {
        assertEquals(5, day.part1())
    }

    @Test
    fun `leadsToInfiniteLoop returns true when the program falls into infinite loop`() {
        assertTrue(day.leadsToInfiniteLoop(day.orderedInstructions))
    }

    @Test
    fun `leadsToInfiniteLoop returns false when the program ends`() {
        val instructions = HashMap(day.orderedInstructions)
        instructions[7] = "nop" to -4
        assertFalse(day.leadsToInfiniteLoop(instructions))
    }

    @Test
    fun `replacementWorks returns false when replacing nop to jmp`() {
        assertFalse(day.replacementWorksFor("nop" to "jmp"))
    }

    @Test
    fun `replacementWorks returns true when we replacing jmp to nop`() {
        assertTrue(day.replacementWorksFor("jmp" to "nop"))
    }

    @Test
    fun `part2 matches example`() {
        assertEquals(8, day.part2())
    }
}