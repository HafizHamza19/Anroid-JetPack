package com.bmcsolution.jetpacklearning.Architecture.LifeCycleAware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bmcsolution.jetpacklearning.R

class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        lifecycle.addObserver(LifeCycleObserver())
        Log.d("tag","Activity Main OnCreate")
    }
}