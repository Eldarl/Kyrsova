package ru.tvorigora.mvp.ui.master

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.tvorigora.mvp.R
import ru.tvorigora.mvp.data.Master
import ru.tvorigora.mvp.data.MasterAdapter
import ru.tvorigora.mvp.data.News
import ru.tvorigora.mvp.data.NewsAdapter
import ru.tvorigora.mvp.databinding.FragmentMasterBinding
import ru.tvorigora.mvp.ui.home.HomeViewModel

class MasterFragment : Fragment() {

    private var _binding: FragmentMasterBinding? = null

    private val binding get() = _binding!!

    private val masterListViewModel by viewModels<MasterViewModel>{
        MasterViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val masterViewModel =
            ViewModelProvider(this).get(MasterViewModel::class.java)

        _binding = FragmentMasterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val masterAdapter = MasterAdapter { master -> adapterOnClick(master) }

        val newsListRV: RecyclerView = binding.masterList
        newsListRV.adapter = masterAdapter

        masterViewModel.masterLiveData.observe(viewLifecycleOwner,
            {
                it?.let{
                    masterAdapter.submitList(it as MutableList<Master>)
                }
            }
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun adapterOnClick(master: Master){

    }
}