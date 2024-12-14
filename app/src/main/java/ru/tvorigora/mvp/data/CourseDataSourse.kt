package ru.tvorigora.mvp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CourseDataSource {
    private val courseList = courseList()
    private val groupList = groupsList()
    private val courseLiveData = MutableLiveData(courseList)
    private val groupLiveData = MutableLiveData(groupList)

    fun getCourseById(id: Long): Course?{
        courseLiveData.value?.let {
                course ->
            return course.firstOrNull{ it.id == id}
        }

        return null
    }

    fun getCourseList(): LiveData<List<Course>> {
        return courseLiveData
    }

    fun getGroupList(): LiveData<List<String>> {
        return groupLiveData
    }

    companion object{
        private var INSTANCE: CourseDataSource? = null

        fun getNewsDataSource(): CourseDataSource {
            return synchronized(CourseDataSource::class) {
                val newInstance = INSTANCE ?: CourseDataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}