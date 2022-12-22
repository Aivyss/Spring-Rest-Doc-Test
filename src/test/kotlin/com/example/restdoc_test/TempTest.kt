package com.example.restdoc_test

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureRestDocs
class TempTest : RestDoc() {
    @Autowired
    private lateinit var om: ObjectMapper

    @InjectMocks
    private lateinit var controller: TestController

    @Test
    fun `테스트 1`() {
        mockMvc(controller).perform(MockMvcRequestBuilders.post("/test1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(TestDto1("name", 123)))
        ).andExpect(status().isOk)
        .andDo(document("index", responseFields(
            fieldWithPath("name").type("String").description("name field"),
            fieldWithPath("age").type("Int").description("age field")
        )))
    }

    @Test
    fun `테스트 2`() {
        val perform = mockMvc(controller).perform(
            MockMvcRequestBuilders.post("/test2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(TestDto2("name", 123, Contact("+82", "1011112222"))))
        )
        perform.andExpect(status().isOk)
            .andDo(document("index2", responseFields(
                fieldWithPath("name").type("String").description("name field"),
                fieldWithPath("age").type("Int").description("age field"),
                subsectionWithPath("contact").type("Contact").description("contact info"),
                fieldWithPath("contact.code").type("String").description("nation code"),
                fieldWithPath("contact.number").type("String").description("phone number"),
            )))
    }
}