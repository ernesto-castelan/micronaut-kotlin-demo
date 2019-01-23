package demo.mn.hello

import javax.inject.Singleton

@Singleton
class HelloService {

    fun capitalizeWords(name: String): String {
        return name.split(" ").map { it.capitalize() }.filter { it != "" }.joinToString(" ")
    }
}
