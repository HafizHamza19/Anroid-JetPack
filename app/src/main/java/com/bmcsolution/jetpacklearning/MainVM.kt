package com.bmcsolution.jetpacklearning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVM:ViewModel() {
    val data=MutableLiveData<String>("Hello")
    fun update()
    {
        data.value="World"
    }
}