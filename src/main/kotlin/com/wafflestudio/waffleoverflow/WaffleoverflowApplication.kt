package com.wafflestudio.waffleoverflow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WaffleoverflowApplication

fun main(args: Array<String>) {
    runApplication<WaffleoverflowApplication>(*args)
}
