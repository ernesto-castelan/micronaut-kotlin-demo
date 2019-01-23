package demo.mn.hello

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import io.micronaut.context.BeanContext

class HelloServiceSpec : StringSpec() {

    val helloService: HelloService = BeanContext.run().getBean(HelloService::class.java)

    init {
        "capitalizeWords returns correct results" {
            forall(
                row("",                     ""),
                row("   ",                  ""),
                row("demo",                 "Demo"),
                row("demo test example",    "Demo Test Example"),
                row("demo   test",          "Demo Test")
            ) { input, expectedResult ->
                helloService.capitalizeWords(input) shouldBe expectedResult
            }
        }
    }
}
