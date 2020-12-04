import PassportData.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day04KtTest {

    private val day = Day04("day04.txt".splitFileToList("\r\n\r\n"))

//    @Test
//    fun `Day04 splits file into passport's line`() {
//        assertAll(
//            { assertEquals("""ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
//byr:1937 iyr:2017 cid:147 hgt:183cm""", day.input[0]) },
//            { assertEquals("""iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
//hcl:#cfa07d byr:1929""", day.input[1]) },
//            { assertEquals("""hcl:#ae17e1 iyr:2013
//eyr:2024
//ecl:brn pid:760753108 byr:1931
//hgt:179cm""", day.input[2]) },
//            { assertEquals("""hcl:#cfa07d eyr:2025 pid:166559648
//iyr:2011 ecl:brn hgt:59in""", day.input[3]) }
//        )
//
//    }

    @Test
    fun `splitToMap returns a Map of Strings representing passport's key-values`() {

        val map = day.splitToMap(
            """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm"""
        )

        assertEquals(8, map.size)
        assertAll(
            { assertEquals("gry", map["ecl"]) },
            { assertEquals("860033327", map["pid"]) },
            { assertEquals("2020", map["eyr"]) },
            { assertEquals("#fffffd", map["hcl"]) },
            { assertEquals("1937", map["byr"]) },
            { assertEquals("2017", map["iyr"]) },
            { assertEquals("147", map["cid"]) },
            { assertEquals("183cm", map["hgt"]) }
        )
    }

    @Test
    fun `part1 matches example`() {
        assertEquals(2, day.part1())
    }

    @Test
    fun `Birth Year validation`() {
        assertFalse(BIRTH_YEAR.validate("NaN"))
        assertFalse(BIRTH_YEAR.validate("1919"))
        assertFalse(BIRTH_YEAR.validate("2003"))

        assertTrue(BIRTH_YEAR.validate("1920"))
        assertTrue(BIRTH_YEAR.validate("2002"))
    }

    @Test
    fun `Issue Year validation`() {
        assertFalse(ISSUE_YEAR.validate("NaN"))
        assertFalse(ISSUE_YEAR.validate("2009"))
        assertFalse(ISSUE_YEAR.validate("2021"))

        assertTrue(ISSUE_YEAR.validate("2010"))
        assertTrue(ISSUE_YEAR.validate("2020"))
    }

    @Test
    fun `Expiration Year validation`() {
        assertFalse(EXPIRATION_YEAR.validate("NaN"))
        assertFalse(EXPIRATION_YEAR.validate("2019"))
        assertFalse(EXPIRATION_YEAR.validate("2031"))

        assertTrue(EXPIRATION_YEAR.validate("2020"))
        assertTrue(EXPIRATION_YEAR.validate("2030"))
    }

    @Test
    fun `Height validation`() {
        assertFalse(HEIGHT.validate("NaH"))

        assertFalse(HEIGHT.validate("149cm"))
        assertFalse(HEIGHT.validate("194cm"))
        assertTrue(HEIGHT.validate("150cm"))
        assertTrue(HEIGHT.validate("193cm"))

        assertFalse(HEIGHT.validate("58in"))
        assertFalse(HEIGHT.validate("77in"))
        assertTrue(HEIGHT.validate("59in"))
        assertTrue(HEIGHT.validate("76in"))
    }

    @Test
    fun `Hair Color validation`() {
        assertFalse(HAIR_COLOR.validate("123456"))

        assertTrue(HAIR_COLOR.validate("#abcdef"))
        assertTrue(HAIR_COLOR.validate("#123456"))
        assertTrue(HAIR_COLOR.validate("#1a2b3c"))

        assertFalse(HAIR_COLOR.validate("#abcdez"))
        assertFalse(HAIR_COLOR.validate("#abcdeff"))
        assertFalse(HAIR_COLOR.validate("#1234567"))
    }

    @Test
    fun `Eye  Color validation`() {
        assertTrue(EYE_COLOR.validate("amb"))
        assertTrue(EYE_COLOR.validate("blu"))
        assertTrue(EYE_COLOR.validate("brn"))
        assertTrue(EYE_COLOR.validate("gry"))
        assertTrue(EYE_COLOR.validate("grn"))
        assertTrue(EYE_COLOR.validate("hzl"))
        assertTrue(EYE_COLOR.validate("oth"))

        assertFalse(EYE_COLOR.validate("aaa"))
    }

    @Test
    fun `Passport Id validation`() {
        assertTrue(PASSPORT_ID.validate("123456789"))
        assertTrue(PASSPORT_ID.validate("000000000"))

        assertFalse(PASSPORT_ID.validate("12345678a"))
        assertFalse(PASSPORT_ID.validate("12345678"))
        assertFalse(PASSPORT_ID.validate("abcdefghi"))
    }

    @Test
    fun `part2 matches example`() {
        assertEquals(2, day.part2())
    }
}