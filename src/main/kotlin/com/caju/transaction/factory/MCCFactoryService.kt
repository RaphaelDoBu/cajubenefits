package com.caju.transaction.factory

import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.service.TransactionService

interface MCCFactoryService {
    fun getTransactionType(category: CategoryEnum): TransactionService

}