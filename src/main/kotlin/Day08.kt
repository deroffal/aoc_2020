import java.lang.IllegalStateException

fun main() {
    val day = Day08("day08.txt".fileToList())
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day08(val input: List<String>) {

    companion object {
        val regex = "^(nop|acc|jmp) (([+\\-])\\d{1,3})$".toRegex()
    }

    val orderedInstructions: Map<Int, Pair<String, Int>> = parseInput()

    var accumulator = 0

    private fun parseInput() = input
        .mapIndexed { idx, line -> idx to (regex.toGroups(line).run { this[0] to this[1].toInt() }) }
        .toMap()

    fun getNextInstructionPosition(position: Int, instructions: Map<Int, Pair<String, Int>> = orderedInstructions): Int {
        val instruction = instructions[position] ?: throw IllegalArgumentException()
        return when (instruction.first) {
            "nop" -> position + 1
            "jmp" -> position + instruction.second
            "acc" -> {
                accumulator += instruction.second
                position + 1
            }
            else -> throw IllegalArgumentException()
        }
    }

    fun part1(): Int {
        var currentPosition = 0
        val visitedPosition = mutableListOf(currentPosition)

        while (getNextInstructionPosition(currentPosition).also { currentPosition = it } !in visitedPosition) {
            visitedPosition.add(currentPosition)
        }

        return accumulator
    }

    fun leadsToInfiniteLoop(instructions: Map<Int, Pair<String, Int>>): Boolean {
        var currentPosition = 0
        var programExited = false

        val visitedPosition = mutableListOf(currentPosition)

        while (!programExited && getNextInstructionPosition(currentPosition, instructions).also { currentPosition = it } !in visitedPosition) {
            visitedPosition.add(currentPosition)
            programExited = currentPosition >= instructions.size
        }
        return !programExited
    }


    fun replacementWorksFor(replacement: Pair<String, String>): Boolean {
        val instructionsToReplacePosition = orderedInstructions.filter { it.value.first == replacement.first }.keys

        return instructionsToReplacePosition.map { position ->
            HashMap(orderedInstructions)
                .apply { this[position] = replacement.second to orderedInstructions[position]!!.second }
        }.all {
            accumulator = 0
            leadsToInfiniteLoop(it)
        }.not()
    }

    fun part2(): Int {
        return when {
            replacementWorksFor("nop" to "jmp") -> accumulator
            replacementWorksFor("jmp" to "nop") -> accumulator
            else -> throw IllegalStateException()
        }
    }
}