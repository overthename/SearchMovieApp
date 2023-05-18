package com.example.shoppingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.data.repository.ShopSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val shopSearchRepository: ShopSearchRepository
) : ViewModel(){

    // UI 상태를 다루는데 필요한 모든 데이터를 ViewModel로 옮겨봅씨다.
    // 아 물론 Adapter는 빼고
//    Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    // Retrofit 동작은 알아서 IO 디스패처로 진행되기 때문에 여기서는 설정을 안해줘도 돼요
    fun searchShops(query: String, start: Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = shopSearchRepository.searchShops(query,10,start)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)

            }
        }
    }



}
