package ru.tvorigora.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import ru.tvorigora.mvp.data.User
import ru.tvorigora.mvp.data.UserDatabase

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "user_database").build()
        var userDao = db.userDao()

        val registerButton = findViewById<Button>(R.id.register_button)

        registerButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.name_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()
            val email = findViewById<EditText>(R.id.email_input).text.toString()

            lifecycleScope.launch {
                // Проверка на существующего пользователя с таким именем
                val existingUser = userDao.getUserByName(username)
                if (existingUser != null) {
                    // Если имя уже занято
                    Toast.makeText(this@RegisterActivity, "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT).show()
                } else {
                    // Создаём нового пользователя
                    val newUser = User(name = username, password = password, email = email)
                    userDao.insert(newUser)
                    // Переходим к экрану авторизации
                    Toast.makeText(this@RegisterActivity, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}