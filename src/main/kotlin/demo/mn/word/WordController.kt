package demo.mn.word

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/word")
class WordController(
    private val wordService: WordService
){
    @Post("/count")
    fun count(@Body command: WordCountCommand): Map<*,*> {
        val count: Int = if (command.unique) wordService.countUnique(command.text)
                         else wordService.count(command.text)
        return mapOf("wordCount" to count)
    }
}
