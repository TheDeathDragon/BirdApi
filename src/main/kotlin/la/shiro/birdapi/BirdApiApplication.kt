package la.shiro.birdapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BirdApiApplication

fun main(args: Array<String>) {
    runApplication<BirdApiApplication>(*args)
}
