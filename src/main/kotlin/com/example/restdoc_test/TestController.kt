package com.example.restdoc_test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class TestController {

    @PostMapping("/")
    fun test(@RequestBody request: TestDto1): TestDto1 = request
}

class TestDto1(
    val name: String,
    val age: Int,
)