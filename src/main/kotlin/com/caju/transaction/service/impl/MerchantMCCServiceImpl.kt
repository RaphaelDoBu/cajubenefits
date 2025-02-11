package com.caju.transaction.service.impl

import com.caju.transaction.dto.request.TransactionRequest
import com.caju.transaction.service.MerchantMCCService
import com.caju.transaction.util.MCCsUtilsBD
import org.springframework.stereotype.Service

@Service
class MerchantMCCServiceImpl(
): MerchantMCCService {

    override fun checkingAndUpdateMccByMerchant(transactionRequest: TransactionRequest) {
        if(!validateMcc(transactionRequest.mcc)) {
            updateMCCByMerchant(transactionRequest)
        }
    }

    private fun updateMCCByMerchant(transactionRequest: TransactionRequest) {
        if(validateMerchant(transactionRequest.merchant)) {
            transactionRequest.mcc = MCCsUtilsBD.getMerchantMCC()[transactionRequest.merchant]!!
        }
    }

    private fun validateMcc(mcc: String): Boolean {
        return MCCsUtilsBD.getMCCs().containsKey(mcc)
    }

    private fun validateMerchant(merchant: String): Boolean {
        return MCCsUtilsBD.getMerchantMCC().containsKey(merchant)
    }
}