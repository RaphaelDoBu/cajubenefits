package com.caju.transaction.controller

import com.caju.transaction.dto.response.TransactionResponse
import com.caju.transaction.enum.AuthorizationStatusEnum
import com.caju.transaction.service.AuthorizerService
import com.caju.transaction.utils.TransactionUtils
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.internal.verification.Times
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class TransactionControllerTest {
    private val authorizerService: AuthorizerService = mock()
    private val transactionController = TransactionController(authorizerService)

    private val mvc = MockMvcBuilders.standaloneSetup(transactionController).build()
    private val mapper = jacksonObjectMapper()

    @Test
    fun `transação efetuada com sucesso`() {
        whenever(authorizerService.authorize(any())).thenReturn(TransactionUtils.getTransactionResponseDTO(code = "00"))

        val result = mvc.perform(
            MockMvcRequestBuilders
                .post("/transactions/authorize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TransactionUtils.getTransactionRequestDTO()))
        ).andExpect(MockMvcResultMatchers.status().isOk).andReturn()

        val body = mapper.readValue(result.response.contentAsString, TransactionResponse::class.java)

        verify(authorizerService, Times(1)).authorize(any())
        Assertions.assertEquals(body.code, AuthorizationStatusEnum.APPROVED.code)
    }

    @Test
    fun `transação rejeitada, não possue saldo suficiente`() {
        whenever(authorizerService.authorize(any())).thenReturn(TransactionUtils.getTransactionResponseDTO(code = "51"))

        val result = mvc.perform(
            MockMvcRequestBuilders
                .post("/transactions/authorize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TransactionUtils.getTransactionRequestDTO()))
        ).andExpect(MockMvcResultMatchers.status().isOk).andReturn()

        val body = mapper.readValue(result.response.contentAsString, TransactionResponse::class.java)

        verify(authorizerService, Times(1)).authorize(any())
        Assertions.assertEquals(body.code, AuthorizationStatusEnum.REJECTED.code)
    }
}