package com.example.sneakers.utils

enum class SortingOrder (val type: Int) {
    ASCENDING_NAME(3),
    DESCENDING_NAME(4),
    ASCENDING_PRICE(2),
    DESCENDING_PRICE(1),
    NORMAL(0)
}