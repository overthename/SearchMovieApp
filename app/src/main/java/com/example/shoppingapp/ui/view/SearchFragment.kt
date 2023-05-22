package com.example.shoppingapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentSearchBinding
import com.example.shoppingapp.ui.adapter.ShopSearchAdapter
import com.example.shoppingapp.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel by viewModels<SearchViewModel>()
    private var shopSearchAdapter: ShopSearchAdapter = ShopSearchAdapter()

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
        moreLoad()

        var i :Int=0
        searchViewModel.start.observe(viewLifecycleOwner) { response ->
            i = response
        }

        searchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val shops = response.items

            shopSearchAdapter.setList(shops)
//            shopSearchAdapter.submitList(shops)
            shopSearchAdapter.notifyItemRangeInserted(i * 10,10)
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
        }
        searchViewModel.onSearchShops("가방")

    }

    fun moreLoad(){
        binding.rvSearchResult.addOnScrollListener(onPostScrollListener)
    }

    private  val onPostScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
            val itemTotalCount = recyclerView.adapter!!.itemCount-1

            if (lastVisibleItemPosition == itemTotalCount) {
                searchViewModel.onSearchShops("가방")
            }
        }
    }



    override fun onDestroyView() {
        _binding = null
        binding.rvSearchResult.removeOnScrollListener(onPostScrollListener)
        super.onDestroyView()
    }

}