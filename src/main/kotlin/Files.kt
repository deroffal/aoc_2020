import java.io.File

/**
 * Read a file in the resource folder with the String as name and return it into String
 */
fun String.fileToString(): String = ClassLoader.getSystemResource(this).readText()

fun String.splitFileToList(separator: String) = this.fileToString().split(separator)

/**
 * Read a file in the resource folder with the String as name and return it into List of Strings
 */
fun String.fileToList() = File(ClassLoader.getSystemResource(this).toURI()).readLines()