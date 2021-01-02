//package com.example.asystentnauczyciela.fragments.list
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.asystentnauczyciela.R
//import com.example.asystentnauczyciela.view_model.adapters.StudentListAdapter
//import com.example.asystentnauczyciela.view_model.StudentViewModel
//import kotlinx.android.synthetic.main.fragment_student_list.*
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//private lateinit var viewModel: StudentViewModel
///**
// * A simple [Fragment] subclass.
// * Use the [StudentListFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class StudentListFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private lateinit var myadapter: StudentListAdapter
//    private lateinit var myLayoutManager: LinearLayoutManager
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        myLayoutManager=LinearLayoutManager(context)
//
//        viewModel=ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
//
//        myadapter=StudentListAdapter(viewModel.readAllData)
//
//        viewModel.readAllData.observe(viewLifecycleOwner, Observer { t->
//            myadapter.notifyDataSetChanged()
//            text_student.text="${t.size}"
//
//        })
//
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_list, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //recyclerView w tym miejscu może być dopiero wywoływany
//        recyclerView=recyclerViewStudents.apply {
//            this.layoutManager=myLayoutManager
//            this.adapter=myadapter
//        }
//
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment StudentListFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            StudentListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}
//
//
//
//
//




















//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.asystentnauczyciela.R
//import com.example.asystentnauczyciela.view_model.StudentViewModel
//import kotlinx.android.synthetic.main.fragment_student_list.view.*
//
//class StudentListFragment : Fragment() {
//
//    private lateinit var mStudentViewModel: StudentViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
//
//        //Recyclerview
//        val adapter = ListAdapter()
//        val recyclerView = view.recyclerViewStudents
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        //StudentViewModel
//        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
//        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer {student ->
//            adapter.setData(student)
//        })
//        view.floatingActionButton.setOnClickListener{
//            findNavController().navigate(R.id.action_studentListFragment_to_studentAddFragment) //switch to new activity with adding student
//        }
//        return view
//    }
//
//}