package ru.tvorigora.mvp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_registration")
data class CourseRegistrationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Int,
    val courseId: Long,
    val courseName: String,
    val groupName: String,
    val childFIO: String
)