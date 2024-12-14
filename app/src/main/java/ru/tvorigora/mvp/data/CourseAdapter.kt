package ru.tvorigora.mvp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tvorigora.mvp.R

class CourseAdapter(private val onClick: (Course) -> Unit):
    ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback) {

    class CourseViewHolder(itemView: View, val onClick: (Course) -> Unit):
        RecyclerView.ViewHolder(itemView) {

        private var currentCourse: Course? = null

        public val courseImage: ImageView = itemView.findViewById(R.id.course_image)
        public val courseCaption: TextView = itemView.findViewById(R.id.course_caption)
        public val courseDescription: TextView = itemView.findViewById(R.id.course_Description)

        init {
            itemView.setOnClickListener{
                currentCourse?.let{
                    onClick(it)
                }
            }
        }

        fun bind(course: Course){
            currentCourse = course

            courseCaption.text = course.caption
            courseDescription.text = course.description

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)

        Glide.with(holder.itemView.context).load(course.imageUrl).circleCrop()
            .error(R.drawable.fav)
            .placeholder(R.drawable.fav).into(holder.courseImage)

    }

    object CourseDiffCallback: DiffUtil.ItemCallback<Course>(){
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

    }
}