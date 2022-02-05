package com.ltl.jetdb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JetdbApplication

fun main(args: Array<String>) {
    runApplication<JetdbApplication>(*args)
}
