package com.example.shoppingapp.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.data.repository.ShopSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val shopSearchRepository: ShopSearchRepository
) : ViewModel(){

    private val _start = MutableLiveData(0)
    val start: LiveData<Int> get() = _start

    private var pageJob: Job = Job().apply{
        cancel()
    }
    @MainThread
    fun onSearchShops(query: String){
        if(pageJob.isCompleted){
            searchShops(query, start.value?:0)
        }
    }

//    Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    fun searchShops(query: String,page:Int) = viewModelScope.launch {

        val response = shopSearchRepository.searchShops(query,10,page * 10 +1)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
                _start.postValue(page+1)

            }
        }
    }


}