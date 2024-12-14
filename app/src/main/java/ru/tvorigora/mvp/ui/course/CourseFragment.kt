package ru.tvorigora.mvp.ui.course

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.tvorigora.mvp.data.Course
import ru.tvorigora.mvp.data.CourseAdapter
import ru.tvorigora.mvp.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null

    private val binding get() = _binding!!

    private val courseListViewModel by viewModels<CourseViewModel>{
        CourseViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val courseViewModel =
            ViewModelProvider(this).get(CourseViewModel::class.java)

        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val courseAdapter = CourseAdapter { course -> adapterOnClick(course) }

        val newsListRV: RecyclerView = binding.courseList
        newsListRV.adapter = courseAdapter

        courseViewModel.courseLiveData.observe(viewLifecycleOwner,
            {
                it?.let{
                    courseAdapter.submitList(it as MutableList<Course>)
                }
            }
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun adapterOnClick(course: Course){
        val intent = Intent(this.context, CourseDetail::class.java)
        intent.putExtra("course id", course.id)
        startActivity(intent)
    }
}