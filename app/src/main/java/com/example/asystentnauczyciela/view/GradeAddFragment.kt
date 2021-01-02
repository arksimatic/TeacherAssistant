package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.GradeViewModel
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_grade_add.*
import kotlinx.android.synthetic.main.fragment_student_add.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GradeAddFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: GradeViewModel

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

        viewModel= ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)






        (activity as AppCompatActivity).supportActionBar?.title = "Dodaj ocene"

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grade_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()

        addGradeButton.setOnClickListener {
                x -> viewModel.addGrade(viewModel.choosenStudentId!!, viewModel.choosenSubjectId!!, gradeSelectSpinner.selectedItem.toString().toInt(), gradeNote.text.toString()) //!!
            view.findNavController().navigate(R.id.action_gradeAddFragment_to_subjectListFragment) //maybe back to the student's grades but remember about adding bundle
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GradeAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initSpinner() {
        val colors = arrayOf("1", "2","3","4","5")
        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            requireContext(), // Context
            android.R.layout.simple_spinner_item, // Layout
            colors // Array
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        gradeSelectSpinner.adapter = adapter
    }
}