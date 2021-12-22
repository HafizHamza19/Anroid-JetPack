package com.bmcsolution.jetpacklearning.Architecture.LiveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    //isme hum changes kr skte hai jese k update k function mai krrhe hai
     private val dataObjects=MutableLiveData<String>("Fun")
    //isme hum changes nhi kr skte one time value set ho jati hai mean data.value kr k nhi kr skte
    val data:LiveData<String>get() = dataObjects
    fun update()
    {
        dataObjects.value="Fin"
    }
}