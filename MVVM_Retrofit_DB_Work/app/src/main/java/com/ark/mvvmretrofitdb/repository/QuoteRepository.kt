package com.ark.mvvmretrofitdb.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ark.mvvmretrofitdb.api.QuoteService
import com.ark.mvvmretrofitdb.db.QuoteDatabase
import com.ark.mvvmretrofitdb.model.QuoteList
import com.ark.mvvmretrofitdb.model.Response
import com.ark.mvvmretrofitdb.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<Response<QuoteList>>()
    val quotes: LiveData<Response<QuoteList>>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val results = quoteService.getQuotes(page)
                if (results.body() != null) {
                    quoteDatabase.quoteDao().addQuotes(results.body()!!.results!!)
                    quotesLiveData.postValue(Response.Success(results.body()))
                }
            } catch (e: Exception) {
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            quotesLiveData.postValue(Response.Success(quoteList))
        }

    }
}