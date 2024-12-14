package ru.tvorigora.mvp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NewsDataSource(){
    private val newsList = newsList()
    private val newsLiveData = MutableLiveData(newsList)

    fun getNewsById(id: Long): News?{
        newsLiveData.value?.let {
            news ->
                return news.firstOrNull{ it.id == id}
        }

        return null
    }

    fun getNewsList(): LiveData<List<News>>{
        return newsLiveData
    }

    companion object{
        private var INSTANCE: NewsDataSource? = null

        fun getNewsDataSource(): NewsDataSource {
            return synchronized(NewsDataSource::class) {
                val newInstance = INSTANCE ?: NewsDataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}