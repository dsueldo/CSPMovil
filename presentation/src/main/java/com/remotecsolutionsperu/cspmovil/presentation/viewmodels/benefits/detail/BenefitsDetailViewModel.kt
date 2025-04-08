package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.domain.entities.benefits.Benefits
import com.remotecsolutionsperu.cspmovil.domain.repositories.BenefitsRepository
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BenefitsDetailViewModel @Inject constructor(
    private val repository: BenefitsRepository,
) : CspAppViewModel() {

    private val _benefitsDetail = MutableStateFlow(Benefits())
    val benefitsDetail: StateFlow<Benefits> = _benefitsDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    fun fetchBenefitsDetail(benefitsId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _benefitsDetail.value = repository.getBenefitsDetail(benefitsId)
                _uiState.value = true
                _isLoading.value = false
                Log.d("BenefitsDetailViewModel", "Benefits Detail: ${_benefitsDetail.value}")
            } catch (e: Exception) {
                _errorMessage.value =
                    "Error al cargar el detalle del beneficio: ${e.localizedMessage}"
                _uiState.value = false
                _isLoading.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
}