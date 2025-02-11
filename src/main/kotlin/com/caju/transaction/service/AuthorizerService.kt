package com.caju.transaction.service

import com.caju.transaction.dto.request.TransactionRequest
import com.caju.transaction.dto.response.TransactionResponse

interface AuthorizerService {

    fun authorize(transactionRequest: TransactionRequest): TransactionResponse
}