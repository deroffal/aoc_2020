import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.function.Function

internal class Day03Test {

    val testInput = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"
    )

    val day = Day03(testInput)

    @Test
    fun `isTreeCoordinate returns true if the coordinate locates a tree (easy case, it's on the input)`() {
        assertFalse(day.isTreeCoordinate(0 to 0))
        assertFalse(day.isTreeCoordinate(1 to 0))
        assertTrue(day.isTreeCoordinate(2 to 0))

        assertTrue(day.isTreeCoordinate(0 to 1))
        assertFalse(day.isTreeCoordinate(1 to 1))
        assertFalse(day.isTreeCoordinate(2 to 1))

        assertFalse(day.isTreeCoordinate(0 to 2))
        assertTrue(day.isTreeCoordinate(1 to 2))
        assertFalse(day.isTreeCoordinate(2 to 2))
    }

    @Test
    fun `isTreeCoordinate returns true if the coordinate locates a tree (position out of initial area)`() {
        assertFalse(day.isTreeCoordinate(11 to 0))
        assertFalse(day.isTreeCoordinate(12 to 0))
        assertTrue(day.isTreeCoordinate(13 to 0))

        assertTrue(day.isTreeCoordinate(11 to 1))
        assertFalse(day.isTreeCoordinate(12 to 1))
        assertFalse(day.isTreeCoordinate(13 to 1))

        assertFalse(day.isTreeCoordinate(11 to 2))
        assertTrue(day.isTreeCoordinate(12 to 2))
        assertFalse(day.isTreeCoordinate(13 to 2))
    }

    @Test
    fun `part1 matches example`() {
        assertEquals(7, day.part1())
    }

    @Test
    fun `countTrees for part 1 matches example`() {
        assertEquals(7, day.countTrees { y -> 3 * y to y })
    }

    @Test
    fun `part2 matches example`() {
        assertEquals(336, day.part2())
    }

    @Test
    fun `Right 1, down 2`() {
        val function = Function<Int, Pair<Int, Int>?> { y -> if (y % 2 == 0) (y / 2) to y else null }
        assertEquals(0 to 0, function.apply(0))
        assertNull(function.apply(1))
        assertEquals(1 to 2, function.apply(2))
        assertNull(function.apply(3))
        assertEquals(2 to 4, function.apply(4))
    }
}