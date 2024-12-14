package ru.tvorigora.mvp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CourseRegistrationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseRegistrationDao(): CourseRegistrationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Функция для получения экземпляра базы данных
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Registered_courses" // Название базы данных
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}