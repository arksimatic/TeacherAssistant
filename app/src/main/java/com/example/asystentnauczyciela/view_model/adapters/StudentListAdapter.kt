package com.example.asystentnauczyciela.view_model.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.view_model.StudentViewModel

class StudentListAdapter(var students: LiveData<List<Student>>, val viewModel: StudentViewModel):RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {

    inner class StudentHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var buttonDeleteStudent = view.findViewById<TextView>(R.id.deleteStudentButton)!!
        var buttonEditStudent = view.findViewById<TextView>(R.id.editStudentButton)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_one_row,parent,false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var textViewID= holder.itemView.findViewById<TextView>(R.id.textViewID)
        var textViewFirstName= holder.itemView.findViewById<TextView>(R.id.textViewFirstName)
        var textViewLastName= holder.itemView.findViewById<TextView>(R.id.textViewLastName)

        textViewID.text= students.value?.get(position)?.studentId.toString()
        textViewFirstName.text=students.value?.get(position)?.firstName
        textViewLastName.text=students.value?.get(position)?.lastName

        holder.itemView.setOnClickListener{
            val currentStudent = students.value?.get(position)
            viewModel.setCurrentStudent(currentStudent)
            //gonna expand this later
//            StudentListFragmentDirections.actionStudentListFragmentToSubjectListFragment(
//                currentStudent.studentId
//            )
            holder.itemView.findNavController().navigate(R.id.action_studentListFragment_to_subjectListFragment)
        }

        holder.buttonDeleteStudent.setOnClickListener {
            viewModel.deleteStudent(students.value?.get(position))
        }

        holder.buttonEditStudent.setOnClickListener{
            viewModel.setCurrentStudent(students.value?.get(position))

            val bundle: Bundle = bundleOf("studentId" to viewModel.currentStudent.value!!.studentId) //are you sure that this is not null?
            holder.itemView.findNavController().navigate(R.id.action_studentListFragment_to_studentEditFragment, bundle)
        }
            //holder.itemView.findNavController().navigate(R.id.action_stationFragment_to_valuesFragment)

    }

    override fun getItemCount(): Int {
        return students.value?.size?:0
    }

}