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
import com.example.asystentnauczyciela.view_model.StudentsOfSubjectViewModel

class StudentsOfSubjectAdapter(var students: LiveData<List<Student>>, val viewModel: StudentsOfSubjectViewModel):RecyclerView.Adapter<StudentsOfSubjectAdapter.StudentHolder>() {

    inner class StudentHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var deleteStudentFromSubjectButton = view.findViewById<TextView>(R.id.deleteStudentFromSubjectButton)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.student_of_subject_list_one_row,parent,false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var textViewID= holder.itemView.findViewById<TextView>(R.id.textViewIdx)
        var textViewFirstName= holder.itemView.findViewById<TextView>(R.id.textViewFirstNamex)
        var textViewLastName= holder.itemView.findViewById<TextView>(R.id.textViewLastNamex)

        textViewID.text= students.value?.get(position)?.studentId.toString()
        textViewFirstName.text=students.value?.get(position)?.firstName
        textViewLastName.text=students.value?.get(position)?.lastName

        holder.deleteStudentFromSubjectButton.setOnClickListener {
            val currentStudent = students.value?.get(position)
            viewModel.deleteStudentFromSubject(currentStudent)
        }
        //this one should be default
        if(!viewModel.isReverted) {
            holder.itemView.setOnClickListener {
                viewModel.setCurrentStudent(students.value?.get(position))

                //pass data here!
                val bundle: Bundle = bundleOf(
                    "studentId" to viewModel.currentStudent.value!!.studentId,
                    "subjectId" to viewModel.choosenSubjectId
                ) //are you sure that this is not null?
                holder.itemView.findNavController().navigate(
                    R.id.action_studentsOfSubjectListFragment_to_gradesOfStudentOfSubjectFragment,
                    bundle
                )
            }
        }
        else
        {
            holder.itemView.setOnClickListener {
                //viewModel.setCurrentStudent(students.value?.get(position))
                viewModel.addStudentToCurrentSubject(students.value?.get(position))
                holder.itemView.findNavController().navigate(R.id.action_studentAddToSubjectFragment_to_subjectListFragment)
            }
        }
        //holder.itemView.setOnClickListener{

            //it's probably gonna be needed for choosing particular grade
//            val currentStudent = students.value?.get(position)
//            viewModel.setCurrentStudent(currentStudent)


            //gonna expand this later
//            StudentListFragmentDirections.actionStudentListFragmentToSubjectListFragment(
//                currentStudent.studentId
//            )
            //holder.itemView.findNavController().navigate(R.id.action_studentListFragment_to_subjectListFragment)
        //}
        //holder.buttonDeleteStudent.setOnClickListener{ viewModel.deleteStudent(students.value?.get(position)) }
        //holder.itemView.findNavController().navigate(R.id.action_stationFragment_to_valuesFragment)
    }

    override fun getItemCount(): Int {
        return students.value?.size?:0
    }
}