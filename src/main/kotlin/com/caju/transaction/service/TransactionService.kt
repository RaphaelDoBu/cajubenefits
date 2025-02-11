package com.caju.transaction.service

import com.caju.transaction.enum.AuthorizationStatusEnum
import com.caju.transaction.enum.CategoryEnum
import org.springframework.stereotype.Service

@Service
interface TransactionService {

    fun debit(category: CategoryEnum,
               accountBalances: MutableMap<CategoryEnum, Double>?,
               totalAmount: Double): AuthorizationStatusEnum
}