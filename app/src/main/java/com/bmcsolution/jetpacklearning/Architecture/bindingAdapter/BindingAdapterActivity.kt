package com.bmcsolution.jetpacklearning.Architecture.bindingAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bmcsolution.jetpacklearning.ImageModel
import com.bmcsolution.jetpacklearning.R
import com.bmcsolution.jetpacklearning.databinding.ActivityBindingAdapterBinding

class BindingAdapterActivity : AppCompatActivity() {
    lateinit var binding:ActivityBindingAdapterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_binding_adapter)
       // setContentView(R.layout.activity_binding_adapter)

        val imageModel=ImageModel("Kotlin","https://kotlinlang.org/assets/images/open-graph/general.png")
        binding.model=imageModel
    }
}