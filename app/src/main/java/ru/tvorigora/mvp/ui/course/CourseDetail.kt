package ru.tvorigora.mvp.ui.course

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import ru.tvorigora.mvp.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer

class CourseDetail : AppCompatActivity() {

    private val courseDetailViewModel by viewModels<CourseDetailViewModel> {
        CourseDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_course_detail)

        var currentCourseId: Long? = null

        val course_detail_image: ImageView = findViewById(R.id.course_detail_image)
        val course_detail_caption: TextView = findViewById(R.id.course_detail_caption)
        val course_detail_description: TextView = findViewById(R.id.course_detail_description)
        val course_detail_button: Button = findViewById(R.id.course_registration_button)
        val groupSpinner: Spinner = findViewById(R.id.group_spinner)

        val bundle: Bundle? = intent.extras
        if(bundle != null) {
            currentCourseId = bundle.getLong("course id")
        }

        currentCourseId?.let {
            var course = courseDetailViewModel.getCourseForId(it)
            course_detail_caption.text = course?.caption
            course_detail_description.text = course?.description

            Glide.with(this).load(course?.imageUrl).circleCrop()
                .error(R.drawable.fav)
                .placeholder(R.drawable.fav).into(course_detail_image)

            courseDetailViewModel.groupList.observe(this, Observer { groups ->
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, groups)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                groupSpinner.adapter = adapter
            })

            course_detail_button.setOnClickListener{
                val selectedGroup = groupSpinner.selectedItem.toString()
                val intent = Intent(this, CourseRegistration::class.java)
                intent.putExtra("course id", course?.id)
                intent.putExtra("selectedGroup", selectedGroup)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.course_detail_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}