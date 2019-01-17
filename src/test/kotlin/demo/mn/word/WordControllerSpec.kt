package demo.mn.word

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object WordControllerSpec: Spek({
    describe("Setup HTTP server and client") {
        val server: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client: HttpClient = HttpClient.create(server.url)

        describe("Test word count") {
            it("works with normal count") {
                val response = client.toBlocking().exchange(
                                   POST("/word/count", WordCountCommand("hello hello", false)),
                                   Map::class.java)
                assertEquals(2, response.body()?.get("wordCount"))
            }

            it("works with unique count") {
                val response = client.toBlocking().exchange(
                                   POST("/word/count", WordCountCommand("hello hello", true)),
                                   Map::class.java)
                assertEquals(1, response.body()?.get("wordCount"))
            }
        }

        afterGroup {
            client.close()
            server.close()
        }
    }
})
