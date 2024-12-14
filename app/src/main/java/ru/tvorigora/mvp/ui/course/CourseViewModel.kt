package ru.tvorigora.mvp.ui.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.tvorigora.mvp.data.CourseDataSource

class CourseViewModel : ViewModel() {
    val courseLiveData = CourseDataSource().getCourseList()
}

class CourseViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}