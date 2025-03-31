package com.effies.draft

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DraftApplication

fun main(args: Array<String>) {
	runApplication<DraftApplication>(*args)
}
