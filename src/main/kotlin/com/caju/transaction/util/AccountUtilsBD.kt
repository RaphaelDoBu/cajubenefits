package com.caju.transaction.util

import com.caju.transaction.enum.CategoryEnum
import com.caju.transaction.model.Account

object AccountUtilsBD {

    fun getAccount(accountId: String): Account {
        return Account(
            accountId = accountId,
            name = "Fulano",
            balances = mutableMapOf(
                CategoryEnum.FOOD to 2000.000,
                CategoryEnum.MEAL to 500.00,
                CategoryEnum.CASH to 20.00
            )
        )

    }
}