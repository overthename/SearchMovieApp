package com.example.shoppingapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    // 페이지를 Fragment에서 관리하는건 좋지 못해요
    // 요런건 ViewModel에서 관리하는게 이쁩니다
    private var start = 1
    private var test = 1


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
        // 데이터바인딩을 Fragment 대신 ViewModel을 사용해서 UI 상태 변화를 ViewModel에서 관리하게 개선해보세요
        binding.apply {
            searchFragment = this@SearchFragment
        }
//        shopSearchViewModel = (activity as MainActivity).shopSearchViewModel

        setupRecyclerView()

        searchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val shops = response.items
            // submitList를 사용해보세요
            // submitList를 썼을 때 어떤 동작이 일어나는지 알아보는 것도 좋겠어요
            shopSearchAdapter.setList(shops)
//            shopSearchAdapter.submitList(shops)
            Log.e("test1",((test-1)*10).toString())
            shopSearchAdapter.notifyItemRangeInserted((start-1) * 10,10)
        }


    }
    private fun setupRecyclerView() {
        // 변수 선언과 동시에 초기화해도 좋겠네요
        shopSearchAdapter = ShopSearchAdapter()

        // 잘 묶었어요
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            // 요거는 xml에서 바로할수도 있겠네요
            layoutManager = GridLayoutManager(requireContext(),2)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = shopSearchAdapter
        }

        // add리스너는 반드시 remove리스너도 존재해요
        // 리스너가 더 이상 필요없을때는 remove로 해제해줘야 누수가 없어요
        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 안(recyclerView.layoutManager as? GridLayoutManager)를 활용하면 더 안전하게 캐스팅 가능해요
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                // layoutManager로도 itemCount에 접근할 수 있어요
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                // 두 조건이 모두 같은 의미로 보이네요
                if (!binding.rvSearchResult.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
//                    shopSearchAdapter.deleteLoading()
//                    Log.e("test2",((++test-1)*10+1).toString())
                    // 페이징에 필요한 작업을 ViewModel로 옮겨서 searchViewModel.searchShops()만 호출해도 되게 개선하면 좋아요
                    searchViewModel.searchShops("가방",(++start-1) * 10 +1)
                }
            }

        })
    }


    // 지금 요구사항이면 ViewModel이 초기화될 때 첫 페이지를 불러오는게 좋겠네요
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
