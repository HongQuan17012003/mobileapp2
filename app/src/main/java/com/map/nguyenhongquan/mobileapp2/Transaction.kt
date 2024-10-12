package com.map.nguyenhongquan.mobileapp2

data class Transaction(
    val id: Int,
    val name: String?,
    val idCateInOut: Int,
    val amount: Double,
    val date: String,
    val note: String?
)