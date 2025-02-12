package com.caju.transaction.utils

import com.caju.transaction.dto.request.TransactionRequest
import com.caju.transaction.dto.response.TransactionResponse

object TransactionUtils {

    fun getTransactionRequestDTO(mcc: String?=null) =
        TransactionRequest(
            accountId = "123",
            totalAmount = 10.00,
            merchant = "Estabelecimento X",
            mcc = mcc?: "5222"
        )

    fun getTransactionResponseDTO(code: String) = TransactionResponse(code = code)

}