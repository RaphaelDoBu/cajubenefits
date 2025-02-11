package com.caju.transaction.enum

enum class AuthorizationStatusEnum(val code: String) {
    APPROVED("00"),
    REJECTED("51"),
    UNKNOWN("07")
}