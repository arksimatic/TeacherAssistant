package com.example.asystentnauczyciela.view_model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.view_model.GradeViewModel

class GradeReportListAdapter(var grades: LiveData<List<Grade>>, val viewModel: GradeViewModel):RecyclerView.Adapter<GradeReportListAdapter.GradeHolder>() {

    inner class GradeHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var confirmGrade = view.findViewById<TextView>(R.id.confirmGradeButton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.grade_report_one_row,parent,false)
        return GradeHolder(view)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        //TODO change id's
        var gradeID= holder.itemView.findViewById<TextView>(R.id.gradeReportId)
        var gradeValue= holder.itemView.findViewById<TextView>(R.id.gradeReportValue)
        var gradeStudent= holder.itemView.findViewById<TextView>(R.id.gradeReportStudent)
        var gradeSubject= holder.itemView.findViewById<TextView>(R.id.gradeReportSubject)

        gradeID.text= grades.value?.get(position)?.gradeId.toString()
        gradeValue.text=grades.value?.get(position)?.value.toString()
        gradeStudent.text=viewModel.getNameOfStudentOfGrade(grades.value?.get(position)?.gradeId!!) //are you sure that not null?
        gradeSubject.text=viewModel.getNameOfSubjectOfGrade(grades.value?.get(position)?.gradeId!!)
        holder.confirmGrade.setOnClickListener {
            viewModel.confirmGrade(grades.value?.get(position)!!.gradeId)
        }
        //gradeStudent.text=grades.value?.get(position)?.note.toString()
        //gradeSubject.text=grades.value?.get(position)?.date.toString()
        //grades.value?.get(position)?.lastName

//        holder.deleteGrade.setOnClickListener {
//            viewModel.deleteGrade(grades.value?.get(position))
//        }
    }

    override fun getItemCount(): Int {
        return grades.value?.size?:0
    }

}
