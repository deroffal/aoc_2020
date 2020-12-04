import java.util.function.Function

fun main() {

    val day = Day04("day04.txt".splitFileToList("\r\n\r\n"))
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day04(val input: List<String>) {

    private val mandatoryData = PassportData.values().filter { it.mandatory }
    private val mandatoryKeys = mandatoryData.map { it.key }

    fun splitToMap(lines: String): Map<String, String> = lines
        .split("\\s".toRegex()).filterNot { it.isBlank() }    // split datas
        .map { it.split(":") }                      // split key/value
        .map { it[0] to it[1] }.toMap()

    fun part1() = input.map { splitToMap(it) }
        .count { passport ->
            checkMandatoryFields(passport)
        }

    fun part2() = input.map { splitToMap(it) }
        .count { passport ->
            checkMandatoryFields(passport) && passport.all { PassportData.findByKey(it.key).validate(it.value) }
        }

    private fun checkMandatoryFields(passport: Map<String, String>) =
        mandatoryKeys.all { field -> field in passport.keys }
}

enum class PassportData(
    val key: String,
    val mandatory: Boolean = true,
    private val validator: Function<String, Boolean> = Function { true }
) {
    BIRTH_YEAR(key = "byr", validator = Function { it.isYearBetween(1920..2002) }),
    ISSUE_YEAR(key = "iyr", validator = Function { it.isYearBetween(2010..2020) }),
    EXPIRATION_YEAR(key = "eyr", validator = Function { it.isYearBetween(2020..2030) }),
    HEIGHT(key = "hgt", validator = Function { it.validateHeight() }),
    HAIR_COLOR(key = "hcl", validator = Function { "^#[0-9a-f]{6}$".toRegex().matches(it) }),
    EYE_COLOR(key = "ecl", validator = Function { "amb|blu|brn|gry|grn|hzl|oth".toRegex().matches(it) }),
    PASSPORT_ID(key = "pid", validator = Function { "^\\d{9}$".toRegex().matches(it) }),
    COUNTRY_ID(key = "cid", mandatory = false);

    fun validate(value: String) = validator.apply(value)

    companion object {
        fun findByKey(key: String) = values().find { it.key == key } ?: throw IllegalArgumentException("incorrect key!")
    }

}

private fun String.isYearBetween(yearRange: IntRange) = this.toIntOrNull()?.run { this in yearRange } ?: false
private fun String.validateHeight(): Boolean = when {
    endsWith("cm") -> removeSuffix("cm").toInt() in 150..193
    endsWith("in") -> removeSuffix("in").toInt() in 59..76
    else -> false
}

/**
 * Only by regex
 */
enum class PassportDataByRegex(
    val key: String,
    val mandatory: Boolean = true,
    private val regex: String = "^.*$",
) {
    BIRTH_YEAR(key = "byr", regex = "^(19[2-9]\\d)|(200[0-2])$"),
    ISSUE_YEAR(key = "iyr", regex = "^20(1[0-9]|20)$"),
    EXPIRATION_YEAR(key = "eyr", regex = "^20(2[0-9]|30)$"),
    HEIGHT(key = "hgt", regex = "^(1([5-8]\\d|9[0-3])cm)|(((59)|(6\\d)|(7[0-6]))in)$"),
    HAIR_COLOR(key = "hcl", regex = "^#[0-9a-f]{6}$"),
    EYE_COLOR(key = "ecl", regex = "amb|blu|brn|gry|grn|hzl|oth"),
    PASSPORT_ID(key = "pid", regex = "^\\d{9}$"),
    COUNTRY_ID(key = "cid", mandatory = false);

    fun validate(value: String) = regex.toRegex().matches(value)

    companion object {
        fun findByKey(key: String) = values().find { it.key == key } ?: throw IllegalArgumentException("incorrect key!")
    }

}