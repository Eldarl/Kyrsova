package ru.tvorigora.mvp.ui.master

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.tvorigora.mvp.data.MasterDataSource

class MasterViewModel : ViewModel() {
    val masterLiveData = MasterDataSource().getMasterList()
}

class MasterViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MasterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MasterViewModel(

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}