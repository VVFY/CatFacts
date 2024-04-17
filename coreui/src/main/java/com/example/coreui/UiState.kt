package com.example.coreui

import com.example.domain.response.Response

open class UiState(
    open val isLoading: Boolean = false,
    open val error: Response.Error? = null
)