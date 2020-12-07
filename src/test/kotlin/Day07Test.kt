import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class Day07Test {

    private val day = Day07(
        listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
        )
    )

    @Test
    fun `parseInputLine returns the name to a map of content (name by count)`() {
        val line = "light red bags contain 1 bright white bag, 2 muted yellow bags."

        val bag = day.parseInputLine(line)
        val content = bag.second.toMap()

        assertEquals("light red", bag.first)

        assertAll(
            { assertEquals(2, content.size) },
            { assertEquals(1, content["bright white"]) },
            { assertEquals(2, content["muted yellow"]) }
        )
    }

    @Test
    fun `Bag#canContain returns false if the content is empty`() {
        assertFalse(Bag("dotted black").canContain("shiny gold"))
    }

    @Test
    fun `Bag#canContain returns true if the bag is directly in its content`() {
        val bag = Bag(
            "muted yellow",
            mutableMapOf(
                Bag("faded blue") to 9
            )
        )
        assertFalse(bag.canContain("shiny gold"))

        bag.bags[Bag("shiny gold")] = 2

        assertTrue(bag.canContain("shiny gold"))
    }

    @Test
    fun `Bag#canContain returns true if the bag is recursively in its content`() {
        val fadedBlue = Bag("faded blue")
        val dottedBlack = Bag("dotted black", mutableMapOf(Bag("shiny gold") to 1))
        val bag = Bag(
            "dark olive",
            mutableMapOf(
                fadedBlue to 9,
                dottedBlack to 3
            )
        )

        assertTrue(bag.canContain("shiny gold"))
    }

    @Test
    fun `part1 matches example`(){
        assertEquals(4, day.part1())
    }

    @Test
    fun `part2 matches example`(){
        day.part1() //to fill the map
        assertEquals(32, day.part2())
    }

}
