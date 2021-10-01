package com.ark.mvvmretrofitdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ark.mvvmretrofitdb.model.QuoteList
import com.ark.mvvmretrofitdb.model.Response
import com.ark.mvvmretrofitdb.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<Response<QuoteList>>
        get() = repository.quotes

}