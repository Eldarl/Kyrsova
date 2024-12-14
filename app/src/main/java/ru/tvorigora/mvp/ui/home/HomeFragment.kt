package ru.tvorigora.mvp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.tvorigora.mvp.data.News
import ru.tvorigora.mvp.data.NewsAdapter
import ru.tvorigora.mvp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val newsListViewModel by viewModels<HomeViewModel>{
        HomeViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val newsAdapter = NewsAdapter { news -> adapterOnClick(news) }

        val newsListRV: RecyclerView = binding.newsList
        newsListRV.adapter = newsAdapter

        homeViewModel.newsLiveData.observe(viewLifecycleOwner,
            {
                it?.let{
                    newsAdapter.submitList(it as MutableList<News>)
                }
            }
            )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun adapterOnClick(news: News){

    }
}