package demo.mn

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("demo.mn")
                .mainClass(Application.javaClass)
                .start()
    }
}
