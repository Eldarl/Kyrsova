package ru.tvorigora.mvp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MasterDataSource {
    private val masterList = masterList()
    private val masterLiveData = MutableLiveData(masterList)

    fun getMasterById(id: Long): Master?{
        masterLiveData.value?.let {
                master ->
            return master.firstOrNull{ it.id == id}
        }

        return null
    }

    fun getMasterList(): LiveData<List<Master>> {
        return masterLiveData
    }

    companion object{
        private var INSTANCE: MasterDataSource? = null

        fun getNewsDataSource(): MasterDataSource {
            return synchronized(MasterDataSource::class) {
                val newInstance = INSTANCE ?: MasterDataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}