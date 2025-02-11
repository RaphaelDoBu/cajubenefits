package com.caju.transaction.service

import com.caju.transaction.enum.AuthorizationStatusEnum
import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.service.impl.CashServiceImpl
import com.caju.transaction.utils.BalancesUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CashServiceTest {
    private val cashService = CashServiceImpl()

    @Test
    fun `saldo suficiente de CASH - Aprovar transação`() {
        val result = cashService.debit(CategoryEnum.CASH, BalancesUtils.getBalances(), 10.00 )

        Assertions.assertEquals(result, AuthorizationStatusEnum.APPROVED)
    }

    @Test
    fun `saldo insuficiente de CASH - Rejeitar transação`() {
        val result = cashService.debit(CategoryEnum.CASH, BalancesUtils.getBalances(), 100.00 )

        Assertions.assertEquals(result, AuthorizationStatusEnum.REJECTED)
    }
}