package demo.mn.word

import javax.inject.Singleton

@Singleton
class WordService {

    fun count(text: String): Int {
        return split(text).size
    }

    fun countUnique(text: String): Int {
        return split(text).distinct().size
    }

    private fun split(text: String): List<String> {
        return text.split(" ").filter { it != "" }
    }
}
