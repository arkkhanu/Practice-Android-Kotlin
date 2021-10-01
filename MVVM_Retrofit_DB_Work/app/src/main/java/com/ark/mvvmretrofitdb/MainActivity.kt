package com.ark.mvvmretrofitdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ark.mvvmretrofitdb.model.Response
import com.ark.mvvmretrofitdb.repository.QuoteRepository
import com.ark.mvvmretrofitdb.viewmodels.MainViewModel
import com.ark.mvvmretrofitdb.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG: String = "ARK-"
    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: QuoteRepository

    //    lateinit var quoteService: QuoteService
//    lateinit var repository: QuoteRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialized()

        mainViewModel.quotes.observe(this, {
            when (it) {
                is Response.Loading -> {
                    Log.d(TAG, "onLoading: ")
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Response.Success -> {
                    Log.d(TAG, "onSuccess: ${it.data.toString()}")
                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
                is Response.Error -> {
                    Log.d(TAG, "onSuccess: ${it.message.toString()}")
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

    private fun initialized() {
//        quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
//        repository = QuoteRepository(quoteService)
        repository = (application as QuoteApplication).quoteRepository
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }


}