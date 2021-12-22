package com.bmcsolution.jetpacklearning.Architecture.dataBindingwithViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.jetpacklearning.MainVM
import com.bmcsolution.jetpacklearning.R
import com.bmcsolution.jetpacklearning.databinding.ActivityDbWithVmBinding

class DbWithVmActivity : AppCompatActivity() {
    lateinit var binding:ActivityDbWithVmBinding
    lateinit var mainVM: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_db_with_vm)
        //setContentView(R.layout.activity_db_with_vm)
        mainVM=ViewModelProvider(this).get(MainVM::class.java)
        //now this work through binding
        /*  mainVM.data.observe(this, Observer {
            binding.title.text=it
        })*/
       /* binding.update.setOnClickListener {
            mainVM.update()
        }*/
        binding.viewmodel=mainVM
        binding.lifecycleOwner=this

    }
}