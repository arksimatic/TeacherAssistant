package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.GradeViewModel
import com.example.asystentnauczyciela.view_model.adapters.GradeReportListAdapter
import kotlinx.android.synthetic.main.fragment_report.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: GradeViewModel

class ReportFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myadapter: GradeReportListAdapter
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
        viewModel= ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)

        //get subject from bundle here
        if(arguments==null) Log.d("Grades", "brak bundla!")

        viewModel.initWithDate()
        myadapter= GradeReportListAdapter(viewModel.readAllData, viewModel)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { t ->
            myadapter.notifyDataSetChanged()
            //text_subject.text = "${t.size}"

        })

        //to zmienia tytul, dodac w kazdym fragmencie
        (activity as AppCompatActivity).supportActionBar?.title = "Report"


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false) //grades list?
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recyclerView w tym miejscu może być dopiero wywoływany
        recyclerView=recyclerViewReportGrades.apply {
            this.layoutManager=myLayoutManager
            this.adapter=myadapter
        }
//        goToAddGrade.setOnClickListener {
//                x -> view.findNavController().navigate(R.id.action_gradesOfStudentOfSubject_to_gradeAddFragment)
//        }
        //goToAddGrade.setOnClickListener { view->view.findNavController().navigate(R.id.action_gradesOfStudentOfSubject_to_gradeAddFragment) }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}