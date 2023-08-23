package com.bogatovnikita.babosiki.view_model

import androidx.lifecycle.viewModelScope
import com.bogatovnikita.babosiki.domain.result.Result
import com.bogatovnikita.babosiki.domain.usecase.GetExchangeRateUseCaseImplementation
import com.bogatovnikita.babosiki.models.CurrencyItem
import com.bogatovnikita.babosiki.models.MainState
import com.bogatovnikita.babosiki.prefs.SharedPreferenceProvider
import com.bogatovnikita.babosiki.sort.SortCriteria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getExchangeRateUseCaseImplementation: GetExchangeRateUseCaseImplementation,
    private val sharedPreferenceProvider: SharedPreferenceProvider
) : BaseViewModel<MainState>(MainState()) {

    init {
        initSortValue()
        updateCurrency()
    }

    fun requestNewCurrency(value: String) {
        sharedPreferenceProvider.saveLastCurrency(value)
        handleCurrencyRequest(value)
    }

    fun updateCurrency() {
        handleCurrencyRequest(sharedPreferenceProvider.getLastCurrency() ?: "RUB")
    }

    private fun handleCurrencyRequest(value: String) {
        startLoading()
        viewModelScope.launch {
            getExchangeRateUseCaseImplementation.invoke(true, value).collect { result ->
                when (result) {
                    is Result.Success -> handleSuccessResult(result.data.rates)
                    is Result.Error -> handleErrorResult()
                }
            }
        }
    }

    private fun handleSuccessResult(rates: Map<String, Double>) {
        mapToList(rates)
        updateState {
            it.copy(
                loading = false
            )
        }
    }

    private fun handleErrorResult() {
        updateState { it.copy(error = true) }
    }

    private fun mapToList(map: Map<String, Double>) {
        val newList = map.map { (key, value) ->
            CurrencyItem(
                key = key,
                value = value
            )
        }
        sortList(newList)
    }

    private fun startLoading() {
        updateState {
            it.copy(
                loading = true,
                error = false
            )
        }
    }

    fun sortList(value: SortCriteria) {
        sharedPreferenceProvider.saveSortValue(value)
        saveSortValue(value)
        sortList()
    }

    private fun initSortValue() {
        val value = when (sharedPreferenceProvider.getSortValue()) {
            SortCriteria.ASCENDING_BY_NAME.name -> SortCriteria.ASCENDING_BY_NAME
            SortCriteria.DESCENDING_BY_NAME.name -> SortCriteria.DESCENDING_BY_NAME
            SortCriteria.ASCENDING_BY_VALUE.name -> SortCriteria.ASCENDING_BY_VALUE
            SortCriteria.DESCENDING_BY_VALUE.name -> SortCriteria.DESCENDING_BY_VALUE
            else -> SortCriteria.ASCENDING_BY_NAME
        }
        saveSortValue(value)
    }

    private fun saveSortValue(value: SortCriteria) {
        updateState {
            it.copy(
                sortValue = value
            )
        }
    }

    private fun sortList(list: List<CurrencyItem> = _screenState.value.currencyList) {
        val newList = when (_screenState.value.sortValue) {
            SortCriteria.ASCENDING_BY_NAME -> {
                list.sortedBy { it.key }
            }

            SortCriteria.DESCENDING_BY_NAME -> {
                list.sortedByDescending { it.key }
            }

            SortCriteria.ASCENDING_BY_VALUE -> {
                list.sortedBy { it.value }
            }

            SortCriteria.DESCENDING_BY_VALUE -> {
                list.sortedByDescending { it.value }
            }
        }
        updateState {
            it.copy(
                currencyList = newList
            )
        }
    }
}
