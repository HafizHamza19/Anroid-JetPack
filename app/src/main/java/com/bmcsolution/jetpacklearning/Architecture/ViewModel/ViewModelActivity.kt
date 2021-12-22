package com.bmcsolution.jetpacklearning.Architecture.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.jetpacklearning.R

class ViewModelActivity : AppCompatActivity() {
   lateinit var mainViewModel:MainViewModel
   lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        textView=findViewById(R.id.count)
        mainViewModel= ViewModelProvider(this,MainViewModelFactory(10))[MainViewModel::class.java]
        setText()
    }
    fun setText()
    {
        textView.text = mainViewModel.count.toString()
    }
    fun Increment(view: View)
    {
        mainViewModel.increment()
        setText()
    }
}