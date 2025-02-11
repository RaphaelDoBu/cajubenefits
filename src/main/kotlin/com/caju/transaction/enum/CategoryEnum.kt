package com.caju.transaction.enum

import com.caju.transaction.util.MCCsUtilsBD

enum class CategoryEnum {
    FOOD,
    MEAL,
    CASH;

    companion object{
        fun getBenefitByMCC(mcc: String): CategoryEnum {
            return MCCsUtilsBD.getMCCs()[mcc] ?: CASH
        }
    }

}