package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_edit.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentEditFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel= ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)


        val choosenStudentId = StudentEditFragmentArgs.fromBundle(requireArguments()).studentId
        viewModel.initWithStudent(choosenStudentId)

        (activity as AppCompatActivity).supportActionBar?.title = "Edytujesz " + viewModel.currentStudent.value?.firstName + ", " + viewModel.currentStudent.value?.lastName

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //confirmation of edit and proceed to studentList
        editButton.setOnClickListener {
                x -> viewModel.editStudent(editName.text.toString(), editSurname.text.toString())
            view.findNavController().navigate(R.id.action_studentEditFragment_to_studentListFragment)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}