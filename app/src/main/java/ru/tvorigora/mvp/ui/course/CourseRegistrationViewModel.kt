package ru.tvorigora.mvp.ui.course

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.tvorigora.mvp.data.AppDatabase
import ru.tvorigora.mvp.data.Course
import ru.tvorigora.mvp.data.CourseDataSource
import ru.tvorigora.mvp.data.CourseRegistrationEntity
import ru.tvorigora.mvp.data.CourseRegistrationRepository

class CourseRegistrationViewModel(private val datasource: CourseDataSource,
                                  private val repository: CourseRegistrationRepository): ViewModel() {

    private val _registrations = MutableLiveData<List<CourseRegistrationEntity>>()
    val registrations: LiveData<List<CourseRegistrationEntity>> get() = _registrations

    fun getCourseForId(id: Long): Course? {
        return datasource.getCourseById(id)
    }

    fun registerUserForCourse(
        courseId: Long, groupName: String,
        userId: Int, childFIO: String
    ) {
        // Получаем данные о курсе
        val course = datasource.getCourseById(courseId)
        if (course != null) {
            viewModelScope.launch {
                repository.registerUser(courseId,
                    course.caption, userId, groupName, childFIO)
            }
        }
    }

    fun getAllRegistrations(userId: Int)  {
        viewModelScope.launch {
            val result = repository.getRegistrationsForUser(userId)
            Log.d("CourseViewModel", "Fetched registrations: $result")
            _registrations.value = result
        }

    }
}

class CourseRegistrationViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(CourseRegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseRegistrationViewModel(
                datasource = CourseDataSource(),
                repository = CourseRegistrationRepository(
                    courseRegistrationDao = AppDatabase.getDatabase(context).courseRegistrationDao()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}