package com.wafflestudio.stackoverflow.domain.pingpong.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/pingpong")
class PingPongController {
    @GetMapping("/")
    fun pingpong(): ResponseEntity<String> {
        return ResponseEntity.ok("pingpong")
    }
}
