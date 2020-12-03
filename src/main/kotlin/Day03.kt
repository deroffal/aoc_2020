import java.util.function.Function

fun main() {
    val day = Day03("day03.txt".fileToList())
    println("Part 1 : ${day.part1()}")
    println("Part 2 : ${day.part2()}")
}

class Day03(val area: List<String>) {

    private val areaLength: Int = area.first().length

    fun isTreeCoordinate(coordinates: Pair<Int, Int>) = area[coordinates.second][coordinates.first % areaLength] == '#'

    fun countTrees(getCoordinateFromY: Function<Int, Pair<Int, Int>?>) =
        area.indices
            .mapNotNull { y -> getCoordinateFromY.apply(y) }
            .count { isTreeCoordinate(it) }

    fun part1() = countTrees { y -> 3 * y to y }

    fun part2() = listOf<Function<Int, Pair<Int, Int>?>>(
        Function { y -> y to y },                                   //Right 1, down 1
        Function { y -> 3 * y to y },                               //Right 3, down 1
        Function { y -> 5 * y to y },                               //Right 5, down 1
        Function { y -> 7 * y to y },                               //Right 7, down 1
        Function { y -> if (y % 2 == 0) (y / 2) to y else null }    //Right 1, down 2
    ).map { countTrees(it).toLong() }
        .reduce { i, j -> i * j }
}

