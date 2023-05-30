package com.example.shoppingapp.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.LikeShop
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

    private val total :MutableList<LikeShop> = mutableListOf()

    private val _searchResult = MutableLiveData<List<LikeShop>>()
    val searchResult: LiveData<List<LikeShop>> get() = _searchResult

    private var pageJob: Job = Job().apply{
        cancel()
    }


    @MainThread
    fun onLoadNextPage(){
        if(pageJob.isCompleted){
            searchShops(querySearch,start.value?:0)
        }
    }

    fun onSearchShop(){

        val q = query.value?:""
        if(!q.isBlank()){
            pageJob.cancel()
            searchShops(q,0)
        }
    }

    private fun searchShops(query: String,page:Int) {

        pageJob=viewModelScope.launch {

            val response = shopSearchRepository.searchShops(query, PAGING_SIZE, page * PAGING_SIZE + 1)
            if (response.isSuccessful) {

                if(page==0){
                    total.clear()
                    querySearch=query
                }


                var newItems =  response.body()?.items?.map{
                    LikeShop(
                        it.brand,
                        it.image,
                        it.lprice,
                        it.productId,
                        it.title,
                        shopSearchRepository.isLikeShop(it.productId)
                    )
                }

                newItems?.let { total.addAll(it.toMutableList()) }
                val t = total.toList()
                _searchResult.postValue(t)
                _start.postValue(page + 1)

            }
        }
    }

    fun updateLikeStates(){
        viewModelScope.launch {
            val response = shopSearchRepository.getLikeShopsId()
            val recent = total.map {
                if(response.contains(it.productId)){
                    it.copy(isLiked = true)
                }else{
                    it.copy(isLiked = false)
                }
            }

            total.clear()
            total.addAll(recent)

            val t = total.toList()
            _searchResult.postValue(t)
        }
    }

    fun onLikeStateChange(shop: LikeShop, isChecked: Boolean) {
        viewModelScope.launch {
            val recent = total.map {
                if(it.productId==shop.productId){
                    it.copy(isLiked = isChecked)
                }else{
                    it
                }
            }

            total.clear()
            total.addAll(recent)

            val t = total.toList()
            _searchResult.postValue(t)

            if(isChecked){
                shopSearchRepository.insertShops(shop.copy(isLiked = true))
            }else{
                shopSearchRepository.deleteShops(shop)
            }
     }

    }


    companion object {
        private const val PAGING_SIZE = 20
    }

}