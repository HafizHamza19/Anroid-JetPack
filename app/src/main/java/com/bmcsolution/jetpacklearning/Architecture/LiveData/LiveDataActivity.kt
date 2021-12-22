package com.bmcsolution.jetpacklearning.Architecture.LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.jetpacklearning.R

class LiveDataActivity : AppCompatActivity() {
    val txt:TextView get() = findViewById(R.id.txt)
    val button:Button get() = findViewById(R.id.update)
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        mainViewModel= ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.data.observe(this, Observer {
            txt.text=it
        })
        button.setOnClickListener {
            mainViewModel.update()
        }
    }
}