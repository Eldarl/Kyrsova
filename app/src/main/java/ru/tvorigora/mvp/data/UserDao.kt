package ru.tvorigora.mvp.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    // Вставка нового пользователя
    @Insert
    suspend fun insert(user: User)

    // Получение пользователя по имени и паролю
    @Query("SELECT * FROM user WHERE name = :name AND password = :password AND email = :email LIMIT 1")
    suspend fun getUser(name: String, password: String, email: String): User?

    // Проверка, существует ли пользователь с таким именем
    @Query("SELECT * FROM user WHERE name = :name LIMIT 1")
    suspend fun getUserByName(name: String): User?
}