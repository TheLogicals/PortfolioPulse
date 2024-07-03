package com.example.upstoxportfolioscreen.ui.holdings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upstoxportfolioscreen.data.HoldingsRepository
import com.example.upstoxportfolioscreen.data.model.Holding
import com.example.upstoxportfolioscreen.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingsViewModel @Inject constructor(private val repository: HoldingsRepository) :
    ViewModel() {

    private val _holdingsEvent = MutableStateFlow<Resource<List<Holding>>>(Resource.Empty)
    val holdingsEvent: StateFlow<Resource<List<Holding>>> get() = _holdingsEvent

    init {
        Log.d("Holding ", "init")
        fetchHoldings()
    }

    private fun fetchHoldings() {
        Log.d("Holding ", "fetchHolding")
        viewModelScope.launch {
            repository.getHoldings().collect { event ->
                _holdingsEvent.value = event
            }
        }
    }
}