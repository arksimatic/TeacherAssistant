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
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.view_model.SubjectViewModel

class SubjectListAdapter(var subjects: LiveData<List<Subject>>, val viewModel: SubjectViewModel):RecyclerView.Adapter<SubjectListAdapter.SubjectHolder>() {

    inner class SubjectHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var buttonDeleteSubject = view.findViewById<TextView>(R.id.deleteSubjectButton)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_list_one_row,parent,false)
        return SubjectHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        var textViewID= holder.itemView.findViewById<TextView>(R.id.textSubjectId)
        var textViewName= holder.itemView.findViewById<TextView>(R.id.textSubjectName)

        textViewID.text= subjects.value?.get(position)?.subjectId.toString()
        textViewName.text=subjects.value?.get(position)?.name

        holder.itemView.setOnClickListener{
            viewModel.setCurrentSubject(subjects.value?.get(position))

            //pass data here!
            val bundle: Bundle = bundleOf("subjectId" to viewModel.currentSubject.value!!.subjectId) //are you sure that this is not null?
            holder.itemView.findNavController().navigate(R.id.action_subjectListFragment_to_studentsOfSubjectListFragment, bundle)
        }
        holder.buttonDeleteSubject.setOnClickListener{ viewModel.deleteSubject(subjects.value?.get(position)) }
        //holder.buttonSubjectStudents.setOnClickListener{ viewModel.subjectStudents(subjects.value?.get(position)) }
    }

    override fun getItemCount(): Int {
        return subjects.value?.size?:0
    }

}