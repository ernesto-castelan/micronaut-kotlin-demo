package demo.mn.word

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/word")
class WordController {

    @Post("/count")
    fun count(@Body command: WordCountCommand): Map<*,*> {
        val count: Int = if (command.unique) WordService.countUnique(command.text)
                         else WordService.count(command.text)
        return mapOf("wordCount" to count)
    }
}
