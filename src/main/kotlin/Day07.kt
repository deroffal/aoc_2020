fun main() {
    val day = Day07("day07.txt".fileToList())
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day07(val input: List<String>) {

    private val bagRegex = "^([a-z]* [a-z]*) bags contain (.*)$".toRegex()
    private val contentRegex = "^(\\d) ([a-z]* [a-z]*) bag?s?.$".toRegex()
    private val noContentRegex = "^.* bags contain no other bags.$".toRegex()

    private val bagsByName = mutableMapOf<String, Bag>()

    fun part1() = input
        .filterNot { it.matches(noContentRegex) }
        .map { parseInputLine(it) }
        .map {
            val bagName = it.first
            val bag = bagsByName.computeIfAbsent(bagName) { Bag(bagName) }

            val content = it.second.map { (name, count) ->
                val innerBag = bagsByName.computeIfAbsent(name) { Bag(name) }
                innerBag to count
            }

            bag.bags.putAll(content)
            bag
        }
        .count { it.canContain("shiny gold") }

    fun part2() = bagsByName["shiny gold"]!!.countBags()

    fun parseInputLine(string: String) = bagRegex.toGroups(string).run {
        this[0] to this[1].split(", ").map { contentRegex.toGroups(it) }.map { it[1] to it[0].toInt() }
    }
}

class Bag(private val name: String, val bags: MutableMap<Bag, Int> = mutableMapOf()) {

    fun canContain(otherBagName: String): Boolean =
        bags.any { it.key.name == otherBagName } || bags.any { it.key.canContain(otherBagName) }

    fun countBags(): Int =
        bags.values.sumBy { it } + bags.map { it.key to it.value }.sumBy { it.second * it.first.countBags() }

}