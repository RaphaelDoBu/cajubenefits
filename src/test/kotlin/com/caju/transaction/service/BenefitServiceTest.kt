package com.caju.transaction.service

import com.caju.transaction.enum.AuthorizationStatusEnum
import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.service.impl.BenefitServiceImpl
import com.caju.transaction.service.impl.CashServiceImpl
import com.caju.transaction.utils.BalancesUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BenefitServiceTest {
    private val cashService: CashServiceImpl = mock()
    private val benefitService = BenefitServiceImpl(cashService)

    @Test
    fun `debita saldo da categoria FOOD`() {
        val result = benefitService.debit(CategoryEnum.FOOD, BalancesUtils.getBalances(), 100.00 )

        Assertions.assertEquals(result, AuthorizationStatusEnum.APPROVED)
    }

    @Test
    fun `saldo insuficiente da categoria FOOD, debitar de CASH`() {
        whenever(cashService.debit(any(), any(), any())).thenReturn(AuthorizationStatusEnum.APPROVED)
        val result = benefitService.debit(CategoryEnum.FOOD, BalancesUtils.getBalances(), 100.00 )

        Assertions.assertEquals(result, AuthorizationStatusEnum.APPROVED)
    }
}