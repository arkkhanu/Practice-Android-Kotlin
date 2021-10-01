package com.ark.mvvmretrofitdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ark.mvvmretrofitdb.repository.QuoteRepository

class MainViewModelFactory(private val repository: QuoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}