package com.bmcsolution.jetpacklearning.Architecture.ViewModel

import androidx.lifecycle.ViewModel

class MainViewModel(var initialize:Int):ViewModel() {
    var count:Int=initialize

    fun increment()
    {
        count++
    }
}