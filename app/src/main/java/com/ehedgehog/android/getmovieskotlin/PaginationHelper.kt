package com.ehedgehog.android.getmovieskotlin

import kotlin.math.ceil

class PaginationHelper {

    companion object {
        const val ITEMS_PER_PAGE: Int = 10
    }

    var currentPage: Int = 0
    var pagesCount: Int = 0
    var totalItems: Int = 0
        set(value) {
            field = value
            pagesCount = ceil(value.toDouble() / ITEMS_PER_PAGE.toDouble()).toInt()
        }

    init {
        currentPage = 1
    }

    fun nextPage() {
        currentPage += 1
    }

    fun reset() {
        currentPage = 1
    }

}