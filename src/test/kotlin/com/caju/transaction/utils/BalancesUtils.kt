package com.caju.transaction.utils

import com.caju.transaction.enum.CategoryEnum

object BalancesUtils {

    fun getBalances() = mutableMapOf(
        CategoryEnum.FOOD to 2000.000,
        CategoryEnum.MEAL to 500.00,
        CategoryEnum.CASH to 20.00
    )
}