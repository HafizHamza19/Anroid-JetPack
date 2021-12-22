package com.bmcsolution.jetpacklearning.Architecture.QuotesApp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context) : ViewModel() {
    var quotes: Array<QuotesModel>
    var index: Int = 0

    init {
        quotes = getQuotesfromjson()
    }

    private fun getQuotesfromjson(): Array<QuotesModel> {
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuotesModel>::class.java)
    }

    fun getQuotes()=quotes[index]
    fun getNextQuotes()=quotes[++index]
    fun getPreviousQuotes()=quotes[--index]
}