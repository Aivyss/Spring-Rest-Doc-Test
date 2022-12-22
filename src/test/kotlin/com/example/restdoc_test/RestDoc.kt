package com.example.restdoc_test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.context.WebApplicationContext

@ExtendWith(RestDocumentationExtension::class)
open abstract class RestDoc {

    protected lateinit var restDocumentation: RestDocumentationContextProvider

    @BeforeEach
    fun initRestDocumentation(restDocumentation: RestDocumentationContextProvider) {
        this.restDocumentation = restDocumentation
    }

    fun mockMvc(controller: Any): MockMvc {
        return  MockMvcBuilders
            .standaloneSetup(controller)
            .apply<StandaloneMockMvcBuilder>(documentationConfiguration(restDocumentation))
            .build()

    }
}