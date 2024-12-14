package ru.tvorigora.mvp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CourseRegistrationDao {
    @Insert
    suspend fun insert(courseRegistrationEntity: CourseRegistrationEntity)

    @Query("SELECT * FROM course_registration WHERE userId = :userId")
    suspend fun getRegistrationsForUser(userId: Int): List<CourseRegistrationEntity>
}