package com.bogatovnikita.babosiki.view_model

import androidx.lifecycle.viewModelScope
import com.bogatovnikita.babosiki.domain.result.Result
import com.bogatovnikita.babosiki.domain.usecase.PopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase
) : BaseViewModel<MainState>(MainState()) {

    fun requestData(nameCurrency: String) {
        viewModelScope.launch(Dispatchers.Default) {
            updateState {
                it.copy(
                    loading = !_screenState.value.loading
                )
            }
            popularUseCase.invoke(nameCurrency).collect {
                when (it) {
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
                                lastUpdate = state.lastUpdate,
                                nameCurrency = state.nameCurrency,
                                currencyList = state.currencyList
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
}