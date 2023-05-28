package com.example.shoppingapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.FragmentLikeBinding
import com.example.shoppingapp.databinding.FragmentSearchBinding
import com.example.shoppingapp.ui.adapter.ShopLikeAdapter
import com.example.shoppingapp.ui.adapter.ShopSearchAdapter
import com.example.shoppingapp.ui.viewmodel.LikeViewModel
import com.example.shoppingapp.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LikeFragment : Fragment() {

    private val likeViewModel by viewModels<LikeViewModel>()
    private var shopLikeAdapter: ShopLikeAdapter = ShopLikeAdapter(this)

    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_like, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewmodel = likeViewModel

        setupRecyclerView()

       likeViewModel.likeShops.observe(viewLifecycleOwner) {
            shopLikeAdapter.submitList(it)

    }}

    private fun setupRecyclerView() {

        binding.rvLikeResult.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = shopLikeAdapter
        }

    }

    fun saveLike(shop: Shop){

        likeViewModel.saveShop(shop)

    }

    fun deleteLike(shop: Shop){
         likeViewModel.deleteShop(shop)
    }




    override fun onDestroyView() {

        _binding = null
        super.onDestroyView()
    }

}