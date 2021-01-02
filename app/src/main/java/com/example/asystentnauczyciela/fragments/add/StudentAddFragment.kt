//package com.example.asystentnauczyciela.fragments.add
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import com.example.asystentnauczyciela.R
//import com.example.asystentnauczyciela.data.entities.Student
//import com.example.asystentnauczyciela.view_model.StudentViewModel
//import kotlinx.android.synthetic.main.fragment_student_add.*
//import kotlinx.android.synthetic.main.fragment_student_add.view.*
//
//class StudentAddFragment : Fragment() {
//
//        private lateinit var mStudentViewModel: StudentViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_student_add, container, false)
//
//        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
//
//        view.Add.setOnClickListener{
//            insertStudentToDatabase()
//        }
//
//        return view
//    }
//
//    private fun insertStudentToDatabase() {
//        val name = addName.text.toString()
//        val surname = addSurname.text.toString()
//
//        //validation?
//        val student = Student(
//            0,
//            name,
//            surname
//        )
//        mStudentViewModel.addStudent(student)
//        Toast.makeText(requireContext(), "Added student", Toast.LENGTH_LONG).show()
//        findNavController().navigate(R.id.action_studentAddFragment_to_studentListFragment)
//    }
//}