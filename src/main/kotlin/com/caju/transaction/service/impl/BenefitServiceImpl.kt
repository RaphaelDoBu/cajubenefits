package com.caju.transaction.service.impl

import com.caju.transaction.enum.AuthorizationStatusEnum
import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.service.TransactionService
import org.springframework.stereotype.Service

@Service("BenefitService")
class BenefitServiceImpl(
    private val cashService: CashServiceImpl
): TransactionService {

    override fun debit(
        category: CategoryEnum,
        accountBalances: MutableMap<CategoryEnum, Double>?,
        totalAmount: Double
    ): AuthorizationStatusEnum {
        val balance = accountBalances?.get(category)

        return balance?.let {
            if(it.compareTo(totalAmount) >= 0) {
                accountBalances[category] = it.minus(totalAmount)
                AuthorizationStatusEnum.APPROVED
            } else {
                cashService.debit(category, accountBalances, totalAmount)
            }
        }?: AuthorizationStatusEnum.UNKNOWN
    }

}