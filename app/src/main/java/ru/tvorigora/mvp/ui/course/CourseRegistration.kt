package ru.tvorigora.mvp.ui.course

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.tvorigora.mvp.MainActivity
import ru.tvorigora.mvp.R

class CourseRegistration : AppCompatActivity() {
    private val courseCourseRegistration by viewModels<CourseRegistrationViewModel> {
        CourseRegistrationViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_course_registration)

        var currentCourseId: Long? = null
        var selectedGroup: String? = null

        val course_registration_caption: TextView = findViewById(R.id.course_registration_caption)
        val course_registration_group: TextView = findViewById(R.id.course_registration_selection)
        val course_registration_button: Button = findViewById(R.id.course_registration_button)

        val fullNameEditText: EditText = findViewById(R.id.course_registration_child_fullName)


        val bundle: Bundle? = intent.extras
        if(bundle != null) {
            currentCourseId = bundle.getLong("course id")
            selectedGroup = bundle.getString("selectedGroup")
        }

        currentCourseId?.let {
            var course = courseCourseRegistration.getCourseForId(it)
            course_registration_caption.text = course?.caption
        }

        course_registration_group.text = "Выбранная группа: $selectedGroup"

        course_registration_button.setOnClickListener{
            val text = getString(R.string.course_registration_confirm_caption)
            Toast.makeText(this.baseContext, text, Toast.LENGTH_SHORT).show()

            var sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
            val userId: Int = sharedPreferences.getInt("user_id", -1)
            val fullName = fullNameEditText.text.toString()


            currentCourseId?.let {
                selectedGroup?.let { group ->
                    courseCourseRegistration.registerUserForCourse(it, group, userId, fullName)
                }
            }

            val intent = Intent(this.baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.course_registration_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}