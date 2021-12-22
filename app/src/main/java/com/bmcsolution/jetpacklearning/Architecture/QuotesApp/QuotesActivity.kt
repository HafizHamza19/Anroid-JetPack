package com.bmcsolution.jetpacklearning.Architecture.QuotesApp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.jetpacklearning.R

class QuotesActivity : AppCompatActivity() {
     val author:TextView get()=findViewById(R.id.quoteAuthor)
     val message:TextView get() = findViewById(R.id.quoteText)
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        mainViewModel= ViewModelProvider(this,MainViewModelFactory(application))[MainViewModel::class.java]
        setText(mainViewModel.getQuotes())
    }

    fun onNext(view: View?)
    {
        setText(mainViewModel.getNextQuotes())
    }

    fun onShare(view: View?) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plan"
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuotes().text)
        startActivity(intent)
    }

    fun onPrevious(view: View?) {
        setText(mainViewModel.getPreviousQuotes())
    }
    private fun setText(quotesModel: QuotesModel)
    {
        author.text = quotesModel.author
        message.text = quotesModel.text
    }
}