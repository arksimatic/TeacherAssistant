package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import kotlinx.android.synthetic.main.fragment_subject_menu.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SubjectMenuFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment

        (activity as AppCompatActivity).supportActionBar?.title = "Menu przedmiotow"
        return inflater.inflate(R.layout.fragment_subject_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listButton.setOnClickListener { view->view.findNavController().navigate(R.id.action_subjectMenuFragment_to_subjectListFragment) }
        addButton.setOnClickListener { view->view.findNavController().navigate(R.id.action_subjectMenuFragment_to_subjectAddFragment) }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubjectMenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}