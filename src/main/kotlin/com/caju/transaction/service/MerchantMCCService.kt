package com.caju.transaction.service

import com.caju.transaction.dto.request.TransactionRequest

interface MerchantMCCService {

    fun checkingAndUpdateMccByMerchant(transactionRequest: TransactionRequest)
}