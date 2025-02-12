package com.caju.transaction.service

import com.caju.transaction.enum.AuthorizationStatusEnum
import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.factory.MCCFactoryService
import com.caju.transaction.service.impl.AuthorizerServiceImpl
import com.caju.transaction.util.AccountUtilsBD
import com.caju.transaction.utils.TransactionUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.internal.verification.Times
import org.mockito.kotlin.*

class AuthorizerServiceTest {
    private val merchantMCCService: MerchantMCCService = mock()
    private val mCCFactoryService: MCCFactoryService = mock()
    private val transactionService: TransactionService = mock()

    private val authorizerService = AuthorizerServiceImpl(merchantMCCService, mCCFactoryService)

    @Test
    fun `aprova autorização de transação de categoria CASH`() {

        doNothing().whenever(merchantMCCService).checkingAndUpdateMccByMerchant(any())
        whenever(mCCFactoryService.getTransactionType(CategoryEnum.CASH)).thenReturn(transactionService)
        whenever(transactionService.debit(any(), any(), any())).thenReturn(
            AuthorizationStatusEnum.APPROVED)

        val result = authorizerService.authorize(TransactionUtils.getTransactionRequestDTO())

        verify(merchantMCCService, Times(1)).checkingAndUpdateMccByMerchant(any())
        verify(mCCFactoryService, Times(1)).getTransactionType(any())
        Assertions.assertEquals(result.code, AuthorizationStatusEnum.APPROVED.code)
    }

    @Test
    fun `aprova autorização de transação de categoria FOOD`() {

        doNothing().whenever(merchantMCCService).checkingAndUpdateMccByMerchant(any())
        whenever(mCCFactoryService.getTransactionType(CategoryEnum.FOOD)).thenReturn(transactionService)
        whenever(transactionService.debit(any(), any(), any())).thenReturn(
            AuthorizationStatusEnum.APPROVED)

        val result = authorizerService.authorize(TransactionUtils.getTransactionRequestDTO(mcc = "5411"))

        verify(merchantMCCService, Times(1)).checkingAndUpdateMccByMerchant(any())
        verify(mCCFactoryService, Times(1)).getTransactionType(any())
        Assertions.assertEquals(result.code, AuthorizationStatusEnum.APPROVED.code)
    }

    @Test
    fun `rejeita autorização de transação de categoria FOOD`() {

        doNothing().whenever(merchantMCCService).checkingAndUpdateMccByMerchant(any())
        whenever(mCCFactoryService.getTransactionType(CategoryEnum.FOOD)).thenReturn(transactionService)
        whenever(transactionService.debit(any(), any(), any())).thenReturn(
            AuthorizationStatusEnum.REJECTED)

        val result = authorizerService.authorize(TransactionUtils.getTransactionRequestDTO(mcc = "5411"))

        verify(merchantMCCService, Times(1)).checkingAndUpdateMccByMerchant(any())
        verify(mCCFactoryService, Times(1)).getTransactionType(any())
        Assertions.assertEquals(result.code, AuthorizationStatusEnum.REJECTED.code)
    }
}