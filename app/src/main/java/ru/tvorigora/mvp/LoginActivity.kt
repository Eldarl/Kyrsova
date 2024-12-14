package ru.tvorigora.mvp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import ru.tvorigora.mvp.data.UserDatabase

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "user_database")
            .build()
        var userDao = db.userDao()

        var sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val usernameEditText = findViewById<EditText>(R.id.name_input)
        val passwordEditText = findViewById<EditText>(R.id.password_input)
        val emailEditText = findViewById<EditText>(R.id.email_input)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener {

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()

            lifecycleScope.launch {
                // Получаем пользователя из базы данных по имени и паролю
                val user = userDao.getUser(username, password, email )

                if (user != null) {
                    // Успешная авторизация
                    // Сохраняем имя пользователя в SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("username", username)
                    editor.putInt("user_id", user.id)
                    editor.apply()

                    // Переходим на главный экран
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // Закрываем LoginActivity, чтобы не возвращаться к ней
                } else {
                    // Неверный логин или пароль
                    runOnUiThread {
                        Toast.makeText(
                            this@LoginActivity,
                            "Неверное имя пользователя или пароль",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        var sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val username: String? = sharedPreferences.getString("username", null)
        if (username != null) {
            // Если имя пользователя есть в SharedPreferences, сразу переходим на главный экран
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()  // Закрываем LoginActivity, чтобы не возвращаться к ней
        }
    }

    fun onRegisterClick(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}