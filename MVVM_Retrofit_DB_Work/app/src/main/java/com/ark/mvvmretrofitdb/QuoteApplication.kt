package com.ark.mvvmretrofitdb

import android.app.Application
import com.ark.mvvmretrofitdb.api.QuoteService
import com.ark.mvvmretrofitdb.api.RetrofitHelper
import com.ark.mvvmretrofitdb.db.QuoteDatabase
import com.ark.mvvmretrofitdb.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database,applicationContext)
    }
}