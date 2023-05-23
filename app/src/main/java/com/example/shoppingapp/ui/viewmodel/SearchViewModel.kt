package com.example.shoppingapp.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.data.model.Shop
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

    val query = MutableLiveData("")

    var querySearch =""

    private val total = mutableListOf<Shop>()

    private val _searchResult = MutableLiveData<List<Shop>>()
    val searchResult: LiveData<List<Shop>> get() = _searchResult

    private var pageJob: Job = Job().apply{
        cancel()
    }


    @MainThread
    fun onLoadNextPage(){
        if(pageJob.isCompleted){

            searchShops(querySearch,start.value?:0)
        }
    }


    private fun searchShops(query: String,page:Int) {

        pageJob=viewModelScope.launch {

            val response = shopSearchRepository.searchShops(query, PAGING_SIZE, page * PAGING_SIZE + 1)
            if (response.isSuccessful) {
                response.body()?.let { body ->

                    if(page==0){
                        total.clear()
                        querySearch=query
                    }

                    total.addAll(body.items)
                    val t = total.toList()
                    _searchResult.postValue(t)
                    _start.postValue(page + 1)

                }
            }
        }
    }

    fun onSearchShop(){

        val q = query.value?:""
        if(!q.isBlank()){
            pageJob.cancel()
            searchShops(q,0)
        }
    }

    companion object {
        private const val PAGING_SIZE = 20
    }

}