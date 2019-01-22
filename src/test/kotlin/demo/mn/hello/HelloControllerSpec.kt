package demo.mn.hello

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer

class HelloControllerSpec : StringSpec() {

    val server: EmbeddedServer = autoClose(ApplicationContext.run(EmbeddedServer::class.java))
    val client: HttpClient = autoClose(HttpClient.create(server.url))

    init {
        "Endpoint /hello{/name} response is correct" {
            forall(
                row("/hello",               "Hello World"),
                row("/hello/john",          "Hello John"),
                row("/hello/john%20doe",    "Hello John Doe")
            ) {endpoint, expectedResult ->
                val response: String = client.toBlocking().retrieve(endpoint)
                response shouldBe expectedResult
            }
        }
    }
}
