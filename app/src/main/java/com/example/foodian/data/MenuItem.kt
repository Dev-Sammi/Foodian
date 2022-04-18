package com.example.foodian.data

import java.text.NumberFormat
import java.util.*

class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val type: Int
) {

    fun getFormattedPrice() = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(price)

}