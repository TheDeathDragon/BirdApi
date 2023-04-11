package la.shiro.birdapi

import la.shiro.birdapi.model.common.DEFAULT_UPLOAD_PATH_PREFIX_LINUX
import la.shiro.birdapi.model.common.DEFAULT_UPLOAD_PATH_PREFIX_WINDOWS
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BirdApiApplication

fun main(args: Array<String>) {
    runApplication<BirdApiApplication>(*args)
    val path = System.getProperty("user.dir")
    println("path: $path")
    DEFAULT_UPLOAD_PATH_PREFIX_WINDOWS = path
    DEFAULT_UPLOAD_PATH_PREFIX_LINUX = path
}
