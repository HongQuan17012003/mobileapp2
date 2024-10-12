package com.map.nguyenhongquan.mobileapp2

data class Category(
    val id: Int,
    val name: String,
    val idParent: Int?,
    val icon: String?,
    val note: String?
)