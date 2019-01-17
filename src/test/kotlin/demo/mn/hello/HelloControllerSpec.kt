package demo.mn.hello

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object HelloControllerSpec: Spek({
    describe("Setup HTTP server and client") {
        val server: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client: HttpClient = HttpClient.create(server.url)

        describe("Test /hello") {
            it("responds Hello World") {
                val response : String = client.toBlocking().retrieve("/hello")
                assertEquals("Hello World", response)
            }
        }

        describe("Test /hello/{name}") {
            it("responds Hello John") {
                val response : String = client.toBlocking().retrieve("/hello/John")
                assertEquals("Hello John", response)
            }

            it("capitalizes name") {
                val response : String = client.toBlocking().retrieve("/hello/john%20doe")
                assertEquals("Hello John Doe", response)
            }
        }

        afterGroup {
            client.close()
            server.close()
        }
    }
})
