package com.caju.transaction.controller

import com.caju.transaction.dto.request.TransactionRequest
import com.caju.transaction.dto.response.TransactionResponse
import com.caju.transaction.service.AuthorizerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(
    private val authorizerService: AuthorizerService
) {

    @PostMapping("/authorize")
    fun sendTransaction(
        @RequestBody transactionRequest: TransactionRequest
    ): ResponseEntity<TransactionResponse>{
        val response = authorizerService.authorize(transactionRequest)
        return ResponseEntity.ok(response)
    }

}