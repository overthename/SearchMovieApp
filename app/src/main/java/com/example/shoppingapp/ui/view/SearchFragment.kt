package com.example.shoppingapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentSearchBinding
import com.example.shoppingapp.ui.adapter.ShopSearchAdapter
import com.example.shoppingapp.ui.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var shopSearchViewModel: SearchViewModel
    private lateinit var shopSearchAdapter: ShopSearchAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

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
        shopSearchViewModel = (activity as MainActivity).shopSearchViewModel

        setupRecyclerView()

        shopSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val shops = response.items
            shopSearchAdapter.submitList(shops)
        }


    }
    private fun setupRecyclerView() {
        shopSearchAdapter = ShopSearchAdapter()

        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager =
                    GridLayoutManager(requireContext(),2)
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = shopSearchAdapter

        }

    }

    fun searchShops(){
//        val query = binding.etSearch.text.toString()
        val query = "가방"
        shopSearchViewModel.searchShops(query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}