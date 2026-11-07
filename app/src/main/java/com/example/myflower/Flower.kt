package com.example.myflower

import java.io.Serializable

data class Flower(
    val id: Int,
    var name: String,
    var shortDesc: String,
    var detailDesc: String,
    var price: Double,
    var unit: String = "bó",
    val imageResId: Int,
    var isSelected: Boolean = false
) : Serializable
