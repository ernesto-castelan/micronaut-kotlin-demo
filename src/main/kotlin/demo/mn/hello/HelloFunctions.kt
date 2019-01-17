package demo.mn.hello

fun capitalizeWords(name: String): String {
    return name.split(" ").map { it.capitalize() }.filter { it != "" }.joinToString(" ")
}
