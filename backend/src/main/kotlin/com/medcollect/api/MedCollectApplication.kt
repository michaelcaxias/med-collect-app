package com.medcollect.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MedCollectApplication

fun main(args: Array<String>) {
    runApplication<MedCollectApplication>(*args)
}
