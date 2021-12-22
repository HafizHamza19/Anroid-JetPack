package com.bmcsolution.jetpacklearning.Architecture.viewModelAndLifecycleScope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.jetpacklearning.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VmAndLcScopeActivity : AppCompatActivity() {
    lateinit var viewModel:ScopeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vm_and_lc_scope)
        viewModel=ViewModelProvider(this).get(ScopeViewModel::class.java)
//        dependency is old thats why not resolve
//        lifecycleScope.launch{
//
//        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            finish()
        }
    }
}