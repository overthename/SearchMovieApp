package com.example.shoppingapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//    private lateinit var shopSearchViewModel: SearchViewModel
    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var shopSearchAdapter: ShopSearchAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var start = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            searchFragment = this@SearchFragment
        }
//        shopSearchViewModel = (activity as MainActivity).shopSearchViewModel

        setupRecyclerView()

        searchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val shops = response.items

            shopSearchAdapter.setList(shops)
//            shopSearchAdapter.submitList(shops)
            shopSearchAdapter.notifyItemRangeInserted((start-1) * 10,10)
        }


    }
    private fun setupRecyclerView() {
        shopSearchAdapter = ShopSearchAdapter()

        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),2)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = shopSearchAdapter
        }

        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvSearchResult.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    shopSearchAdapter.deleteLoading()
                    searchViewModel.searchShops("가방",(++start-1) * 10 +1)
                }
            }

        })
    }


    fun searchShops(){
//        val query = binding.etSearch.text.toString()
        val query = "가방"
        searchViewModel.searchShops(query,1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}