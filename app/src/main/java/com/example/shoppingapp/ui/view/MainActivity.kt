package com.example.shoppingapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.data.repository.ShopSearchRepositoryImpl
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    lateinit var shopSearchViewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val shopSearchRepository = ShopSearchRepositoryImpl()
//        val factory = ShopSearchViewModelFactory(shopSearchRepository)
//        shopSearchViewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
    }
}