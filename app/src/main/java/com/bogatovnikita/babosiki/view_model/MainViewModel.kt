package com.bogatovnikita.babosiki.view_model

import androidx.lifecycle.viewModelScope
import com.bogatovnikita.babosiki.domain.result.Result
import com.bogatovnikita.babosiki.domain.usecase.PopularUseCase
import com.bogatovnikita.babosiki.models.CurrencyItem
import com.bogatovnikita.babosiki.models.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase
) : BaseViewModel<MainState>(MainState()) {

    fun requestData(nameCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState {
                it.copy(
                    loading = !_screenState.value.loading
                )
            }
            popularUseCase.invoke(nameCurrency).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        updateState { state ->
                            state.copy(loading = !_screenState.value.loading)
                        }
                    }

                    is Result.Success -> {
                        updateState { state ->
                            state.copy(
                                loading = false,
                                error = false,
                                lastUpdate = result.data.date,
                                nameCurrency = result.data.baseCurrency,
                                currencyList = matToList(result.data.rates)
                            )
                        }
                    }

                    is Result.Error -> {
                        updateState { state ->
                            state.copy(error = !_screenState.value.error)
                        }
                    }
                }
            }
        }
    }

    private fun matToList(map: Map<String, Double>): List<CurrencyItem> {
        return map.map { (key, value) ->
            CurrencyItem(
                key = key,
                value = value
            )
        }
    }
}