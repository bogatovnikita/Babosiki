package com.bogatovnikita.babosiki.view_model

import androidx.lifecycle.viewModelScope
import com.bogatovnikita.babosiki.domain.result.Result
import com.bogatovnikita.babosiki.domain.usecase.GetExchangeRateUseCaseImplementation
import com.bogatovnikita.babosiki.models.CurrencyItem
import com.bogatovnikita.babosiki.models.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getExchangeRateUseCaseImplementation: GetExchangeRateUseCaseImplementation
) : BaseViewModel<MainState>(MainState()) {

    init {
        updateCurrency()
    }

    fun requestNewCurrency(value: String) {
        handleCurrencyRequest(value, true)
    }

    fun updateCurrency() {
        handleCurrencyRequest(_screenState.value.currentCurrency, false)
    }

    private fun handleCurrencyRequest(value: String, forceUpdate: Boolean) {
        startLoading()
        viewModelScope.launch {
            getExchangeRateUseCaseImplementation.invoke(forceUpdate, value).collect { result ->
                when (result) {
                    is Result.Success -> handleSuccessResult(result.data.rates)
                    is Result.Error -> handleErrorResult()
                }
            }
        }
    }

    private fun handleSuccessResult(rates: Map<String, Double>) {
        updateState {
            it.copy(
                currencyList = mapToList(rates),
                loading = false
            )
        }
    }

    private fun handleErrorResult() {
        updateState { it.copy(error = true) }
    }

    private fun mapToList(map: Map<String, Double>): List<CurrencyItem> {
        return map.map { (key, value) ->
            CurrencyItem(
                key = key,
                value = value
            )
        }
    }

    private fun startLoading() {
        updateState {
            it.copy(
                loading = true,
                error = false
            )
        }
    }
}
