package com.bogatovnikita.babosiki.models

import com.bogatovnikita.babosiki.sort.SortCriteria

data class MainState(
    val nameCurrency: String = "",
    val loading: Boolean = true,
    val error: Boolean = false,
    val currencyList: List<CurrencyItem> = mutableListOf(),
    val sortValue: SortCriteria = SortCriteria.ASCENDING_BY_NAME
)
