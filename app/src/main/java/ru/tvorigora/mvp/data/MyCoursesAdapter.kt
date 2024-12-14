package ru.tvorigora.mvp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.tvorigora.mvp.R

class MyCoursesAdapter(
    private val courseRegistrations: List<CourseRegistrationEntity>
) : RecyclerView.Adapter<MyCoursesAdapter.CourseRegistrationViewHolder>() {

    class CourseRegistrationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fullNameTextView: TextView = view.findViewById(R.id.course_registration_full_name)
        val courseNameTextView: TextView = view.findViewById(R.id.course_registration_course_name)
        val groupTextView: TextView = view.findViewById(R.id.course_registration_group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseRegistrationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_course_item, parent, false)
        return CourseRegistrationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseRegistrationViewHolder, position: Int) {
        val courseRegistration = courseRegistrations[position]
        holder.fullNameTextView.text = courseRegistration.childFIO
        holder.courseNameTextView.text = courseRegistration.courseName
        holder.groupTextView.text = courseRegistration.groupName
    }

    override fun getItemCount(): Int = courseRegistrations.size
}

