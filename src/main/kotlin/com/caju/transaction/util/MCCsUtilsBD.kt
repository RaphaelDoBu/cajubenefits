package com.caju.transaction.util

import com.caju.transaction.enum.CategoryEnum

object MCCsUtilsBD {

    fun getMCCs() = mapOf(
        "5411" to CategoryEnum.FOOD,
        "5412" to CategoryEnum.FOOD,
        "5811" to CategoryEnum.MEAL,
        "5812" to CategoryEnum.MEAL
    )

    fun getMerchantMCC() = mapOf(
        "UBER TRIP                   SAO PAULO BR" to "5811",
        "UBER EATS                   SAO PAULO BR" to "5812",
        "PAG*JoseDaSilva          RIO DE JANEI BR" to "1122",
        "PICPAY*BILHETEUNICO           GOIANIA BR" to "1122"
    )

}