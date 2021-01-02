package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.StudentsOfSubjectViewModel
import com.example.asystentnauczyciela.view_model.adapters.StudentsOfSubjectAdapter
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_grades_of_student_of_subject.*
import kotlinx.android.synthetic.main.fragment_students_of_subject_list.*
import kotlinx.android.synthetic.main.fragment_subject_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: StudentsOfSubjectViewModel

class StudentsOfSubjectListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myadapter: StudentsOfSubjectAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

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
        myLayoutManager=LinearLayoutManager(context)

        viewModel= ViewModelProvider(requireActivity()).get(StudentsOfSubjectViewModel::class.java)

        //get subject from bundle here
        if(arguments==null) Log.d("StudentsOfSubject","brak bundla!")

        val choosenSubject = StudentsOfSubjectListFragmentArgs.fromBundle(requireArguments()).subjectId
        viewModel.initWithSubject(choosenSubject)
        myadapter= StudentsOfSubjectAdapter(viewModel.readAllData, viewModel)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { t->
            myadapter.notifyDataSetChanged()
            //text_subject.text="${t.size}"

        })

        var title = viewModel.getNameOfChoosenSubject()
        (activity as AppCompatActivity).supportActionBar?.title = title
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_of_subject_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recyclerView w tym miejscu może być dopiero wywoływany
        recyclerView=recyclerViewStudentsOfSubject.apply {
            this.layoutManager=myLayoutManager
            this.adapter=myadapter
        }

        goToAddStudentToSubject.setOnClickListener { x ->
                val bundle = bundleOf("subjectId" to viewModel.choosenSubjectId)
                view.findNavController().navigate(R.id.action_studentsOfSubjectListFragment_to_studentAddToSubjectFragment, bundle)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentsOfSubjectListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}