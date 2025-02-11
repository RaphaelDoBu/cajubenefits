package com.caju.transaction.dto.request

data class TransactionRequest(
    val accountId: String,
    val totalAmount: Double,
    val merchant: String,
    var mcc: String
)