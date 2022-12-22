package com.example.restdoc_test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class TestController {

    @PostMapping("/test1")
    fun test(@RequestBody request: TestDto1): TestDto1 = request

    @PostMapping("/test2")
    fun test2(@RequestBody request: TestDto2): TestDto2 = request
}

class TestDto1(
    val name: String,
    val age: Int,
)

class TestDto2(
    val name: String,
    val age: Int,
    val contact: Contact,
)

class Contact(
    val code: String,
    val number: String,
)