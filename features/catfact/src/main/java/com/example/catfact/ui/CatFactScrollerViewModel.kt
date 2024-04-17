package com.example.catfact.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.response.Response
import com.example.domain.usecase.FetchCatFactUseCase
import com.example.domain.usecase.GetCatFactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactScrollerViewModel @Inject constructor(
    private val getCatFactsUseCase: GetCatFactsUseCase,
    private val fetchCatFactUseCase: FetchCatFactUseCase,
) : ViewModel() {
    private var catFactFetchJob: Job? = null

    private val _state: MutableStateFlow<CatFactScrollerScreenState> = MutableStateFlow(CatFactScrollerScreenState())
    val state: StateFlow<CatFactScrollerScreenState> = _state

    init {
        getCatFactList()
    }

    private fun getCatFactList() {
        getCatFactsUseCase()
            .onStart { _state.update { it.copy(isLoading = true) } }
            .onEach { list ->
                _state.update { it.copy(catFactList = list, isLoading = false) }
                if (list.isNotEmpty()) {
                    delay(500)
                    _state.update { it.copy(showNextFact = true) }
                } else {
                    fetchCatFact()
                }
            }
            .launchIn(viewModelScope)
    }

    fun fetchCatFact() {
        if (catFactFetchJob?.isActive == true) return
        viewModelScope
            .launch {
                _state.update { it.copy(isLoading = true) }
                fetchCatFactUseCase()
                    .onSuccess {
                        _state.update { it.copy(isLoading = false) }
                    }
                    .onError { e -> _state.update { it.copy(isLoading = false, error = e) } }
            }
            .also { catFactFetchJob = it }
    }

    fun onNextFactShown() {
        _state.update { it.copy(showNextFact = false) }
    }

    fun errorShown() {
        _state.update {
            it.copy(error = null)
        }
    }
}