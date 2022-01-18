package com.wafflestudio.waffleoverflow.domain.pingpong.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ping")
class PingPongController {
    @GetMapping("/")
    fun pingpong(): ResponseEntity<String> {
        return ResponseEntity.ok("pong")
    }
}
