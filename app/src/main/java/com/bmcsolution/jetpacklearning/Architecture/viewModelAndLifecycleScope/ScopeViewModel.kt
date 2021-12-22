package com.bmcsolution.jetpacklearning.Architecture.viewModelAndLifecycleScope

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScopeViewModel:ViewModel() {
    //ViewModelScope hamare viewmodel seattach hota hai jb view model destroy hoga tw viewmodelScope(Coroutine) bhi Destry hojaega
    init {
        viewModelScope.launch {

            while (true)
            {
                delay(500)
                Log.d("Tag","Hello")

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("Tag","View Model Destroyed")

    }
}