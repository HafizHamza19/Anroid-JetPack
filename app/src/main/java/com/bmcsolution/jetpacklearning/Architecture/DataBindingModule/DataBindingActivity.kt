package com.bmcsolution.jetpacklearning.Architecture.DataBindingModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bmcsolution.jetpacklearning.ModelClass
import com.bmcsolution.jetpacklearning.R
import com.bmcsolution.jetpacklearning.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
        lateinit var bindingActivity:ActivityDataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
       bindingActivity= DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        val modelobj= ModelClass("hi","hello")
        bindingActivity.model=modelobj
       /* bindingActivity.binding.text="Hello"
        bindingActivity.data.text="World"*/

}
}