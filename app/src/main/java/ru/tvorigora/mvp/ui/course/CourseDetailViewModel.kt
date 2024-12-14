package ru.tvorigora.mvp.ui.course

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.tvorigora.mvp.data.Course
import ru.tvorigora.mvp.data.CourseDataSource

class CourseDetailViewModel(private val datasource: CourseDataSource): ViewModel() {

    val groupList: LiveData<List<String>> = datasource.getGroupList()

    fun getCourseForId(id: Long): Course? {
        return datasource.getCourseById(id)
    }
}

class CourseDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(CourseDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseDetailViewModel(
                datasource = CourseDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}