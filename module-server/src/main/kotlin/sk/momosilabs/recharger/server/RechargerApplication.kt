package sk.momosilabs.recharger.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class RechargerApplication

fun main(args: Array<String>) {
    runApplication<RechargerApplication>(*args)
}
