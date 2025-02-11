package com.caju.transaction.model

import com.caju.transaction.enum.CategoryEnum

data class Account(
    val accountId: String,
    val name: String = "José",
    var balances: MutableMap<CategoryEnum, Double>?= null
    //todo: others attribuites
) {
}
