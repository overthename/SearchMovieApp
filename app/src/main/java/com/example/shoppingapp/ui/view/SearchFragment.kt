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
import com.example.shoppingapp.data.model.LikeShop
import com.example.shoppingapp.databinding.FragmentSearchBinding
import com.example.shoppingapp.ui.adapter.ShopSearchAdapter
import com.example.shoppingapp.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel by viewModels<SearchViewModel>()
    private var shopSearchAdapter: ShopSearchAdapter = ShopSearchAdapter(
        onLikeStateChanged = { shop, isChecked -> searchViewModel.onLikeStateChange(shop,isChecked) }
    )

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewmodel = searchViewModel

        setupRecyclerView()
        searchViewModel.updateLikeStates()
        searchViewModel.searchResult.observe(viewLifecycleOwner) { shops ->

            shopSearchAdapter.submitList(shops)
        }

    }

    private fun setupRecyclerView() {

        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = shopSearchAdapter
            addOnScrollListener(onPostScrollListener)
        }

    }


    private  val onPostScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
            val itemTotalCount = layoutManager.itemCount-1

            if (lastVisibleItemPosition == itemTotalCount) {
                searchViewModel.onLoadNextPage()
            }
        }
    }



    override fun onDestroyView() {
        binding.rvSearchResult.removeOnScrollListener(onPostScrollListener)
        _binding = null
        super.onDestroyView()
    }

}