package com.caju.transaction.model

import java.util.*

data class Transaction(
    val id: UUID,
    val accountId: String,
    val amount: Double,
    val merchant: String,
    val mcc: String
)
