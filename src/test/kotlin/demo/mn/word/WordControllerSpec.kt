package demo.mn.word

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer

class WordControllerSpec : StringSpec() {

    val server: EmbeddedServer = autoClose(ApplicationContext.run(EmbeddedServer::class.java))
    val client: HttpClient = autoClose(HttpClient.create(server.url))

    init {
        "Normal word count is correct" {
            val body = WordCountCommand(text = "hello hello", unique = false)
            val response = client.toBlocking().exchange(POST("/word/count", body), Map::class.java)
            response.body()?.get("wordCount") shouldBe 2
        }

        "Unique word count is correct" {
            val body = WordCountCommand(text = "hello hello", unique = true)
            val response = client.toBlocking().exchange(POST("/word/count", body), Map::class.java)
            response.body()?.get("wordCount") shouldBe 1
        }
    }
}
