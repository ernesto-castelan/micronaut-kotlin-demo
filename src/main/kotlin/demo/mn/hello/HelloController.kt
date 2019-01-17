package demo.mn.hello

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/hello")
class HelloController {

    @Get("{/name}")
    @Produces(MediaType.TEXT_PLAIN)
    fun index(name: String?): String {
        val displayName = if(name != null) capitalizeWords(name) else "World"
        return "Hello $displayName"
    }
}
