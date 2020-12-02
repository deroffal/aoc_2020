import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day02Test {

    @Test
    fun `examples match regex`() {
        val day = Day02()
        val regex = day.regex

        assertTrue(regex matches "1-3 a: abcde")
        assertTrue(regex matches "1-3 b: cdefg")
        assertTrue(regex matches "2-9 c: ccccccccc")
    }

    @Test
    fun `regex groups example's values`() {
        val day = Day02()
        val regex = day.regex

        assertEquals(
            listOf("1-3 a: abcde", "1", "3", "a", "abcde"),
            regex.find("1-3 a: abcde")?.groupValues
        )

        assertEquals(
            listOf("1-3 b: cdefg", "1", "3", "b", "cdefg"),
            regex.find("1-3 b: cdefg")?.groupValues
        )

        assertEquals(
            listOf("2-9 c: ccccccccc", "2", "9", "c", "ccccccccc"),
            regex.find("2-9 c: ccccccccc")?.groupValues
        )
    }

    @Test
    fun `PasswordByOccurencyMatcher matches or not examples`() {
        assertTrue(PasswordByOccurencyMatcher(1, 3, 'a', "abcde").matches())
        assertFalse(PasswordByOccurencyMatcher(1, 3, 'b', "cdefg").matches())
        assertTrue(PasswordByOccurencyMatcher(2, 9, 'c', "ccccccccc").matches())
    }

    @Test
    fun `PasswordByPositionMatcher matches or not examples`() {
        assertTrue(PasswordByPositionMatcher(1, 3, 'a', "abcde").matches())
        assertFalse(PasswordByPositionMatcher(1, 3, 'b', "cdefg").matches())
        assertFalse(PasswordByPositionMatcher(2, 9, 'c', "ccccccccc").matches())
    }
}