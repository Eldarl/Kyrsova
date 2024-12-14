package ru.tvorigora.mvp.ui.course

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.tvorigora.mvp.data.MyCoursesAdapter
import ru.tvorigora.mvp.databinding.FragmentMyCourseBinding

class MyCoursesFragment : Fragment() {

    private var _binding: FragmentMyCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseRegistrationViewModel: CourseRegistrationViewModel
    private lateinit var courseRegistrationAdapter: MyCoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyCourseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Инициализация адаптера для RecyclerView
        courseRegistrationAdapter = MyCoursesAdapter(emptyList())
        binding.recyclerView.adapter = courseRegistrationAdapter

        // Получаем userId из SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userId: Int = sharedPreferences.getInt("user_id", -1)

        // Инициализация ViewModel с использованием Factory
        val factory = CourseRegistrationViewModelFactory(requireContext())
        courseRegistrationViewModel = ViewModelProvider(this, factory).get(CourseRegistrationViewModel::class.java)

        // Получаем данные из ViewModel и подписываемся на LiveData
        courseRegistrationViewModel.getAllRegistrations(userId)
        courseRegistrationViewModel.registrations.observe(viewLifecycleOwner) { courseRegistrations ->
            Log.d("MyCoursesFragment", "LiveData observed: $courseRegistrations")
            courseRegistrations?.let {
                // Обновляем адаптер с новыми данными
                courseRegistrationAdapter = MyCoursesAdapter(it)
                binding.recyclerView.adapter = courseRegistrationAdapter
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
