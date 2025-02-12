package com.caju.transaction.service.impl

import com.caju.transaction.dto.request.TransactionRequest
import com.caju.transaction.dto.response.TransactionResponse
import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.factory.MCCFactoryService
import com.caju.transaction.service.AuthorizerService
import com.caju.transaction.service.MerchantMCCService
import com.caju.transaction.util.AccountUtilsBD
import org.springframework.stereotype.Service

@Service
class AuthorizerServiceImpl(
    private val merchantMCCService: MerchantMCCService,
    private val mCCFactoryService: MCCFactoryService
): AuthorizerService {

    override fun authorize(transactionRequest: TransactionRequest): TransactionResponse {
        merchantMCCService.checkingAndUpdateMccByMerchant(transactionRequest)
        val category = CategoryEnum.getBenefitByMCC(transactionRequest.mcc)
        val transactionType = mCCFactoryService.getTransactionType(category)
        val account = AccountUtilsBD.getAccount(transactionRequest.accountId) //todo: buscar em um BD pelo accountId e retornar objeto e seus saldos
        val status = transactionType.debit(category, account.balances, transactionRequest.totalAmount)

        return TransactionResponse(
            code = status.code
        )
    }
}