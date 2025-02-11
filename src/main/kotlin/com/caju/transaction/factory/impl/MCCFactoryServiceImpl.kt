package com.caju.transaction.factory.impl

import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.factory.MCCFactoryService
import com.caju.transaction.service.TransactionService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class MCCFactoryServiceImpl(
    @Qualifier("BenefitService")
    private val benefitService: TransactionService,
    @Qualifier("CashService")
    private val cashService: TransactionService
): MCCFactoryService {

    override fun getTransactionType(category: CategoryEnum): TransactionService {
        return when(category) {
            CategoryEnum.FOOD, CategoryEnum.MEAL -> benefitService
            else -> cashService
        }
    }
}