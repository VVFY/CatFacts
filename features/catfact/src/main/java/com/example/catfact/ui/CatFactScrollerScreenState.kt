package com.example.catfact.ui

import com.example.coreui.UiState
import com.example.domain.model.CatFact
import com.example.domain.response.Response


data class CatFactScrollerScreenState(
    override val isLoading: Boolean = false,
    val catFactList: List<CatFact> = emptyList(),
    val showNextFact: Boolean = false,
    override val error: Response.Error? = null
): UiState()