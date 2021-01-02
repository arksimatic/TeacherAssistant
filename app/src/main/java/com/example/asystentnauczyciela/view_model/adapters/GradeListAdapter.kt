package com.example.asystentnauczyciela.view_model.adapters
//niezrobiony
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
import com.example.asystentnauczyciela.view_model.StudentViewModel

class GradeListAdapter(var grades: LiveData<List<Grade>>, val viewModel: GradeViewModel):RecyclerView.Adapter<GradeListAdapter.GradeHolder>() {

    inner class GradeHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var deleteGrade = view.findViewById<TextView>(R.id.deleteGrade)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.grade_list_one_row,parent,false)
        return GradeHolder(view)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        var gradeID= holder.itemView.findViewById<TextView>(R.id.gradeId)
        var gradeValue= holder.itemView.findViewById<TextView>(R.id.gradeValue)
        var gradeNote= holder.itemView.findViewById<TextView>(R.id.gradeNote)
        var gradeDate= holder.itemView.findViewById<TextView>(R.id.gradeDate)

        gradeID.text= grades.value?.get(position)?.gradeId.toString()
        gradeValue.text=grades.value?.get(position)?.value.toString()
        gradeNote.text=grades.value?.get(position)?.note.toString()
        gradeDate.text=grades.value?.get(position)?.date.toString()
                //grades.value?.get(position)?.lastName

        holder.deleteGrade.setOnClickListener {
            viewModel.deleteGrade(grades.value?.get(position))
        }
//        holder.itemView.setOnClickListener{
//            val currentStudent = students.value?.get(position)
//            viewModel.setCurrentStudent(currentStudent)
            //gonna expand this later
//            StudentListFragmentDirections.actionStudentListFragmentToSubjectListFragment(
//                currentStudent.studentId
//            )
//            holder.itemView.findNavController().navigate(R.id.action_studentListFragment_to_subjectListFragment)
//        }
//        holder.buttonDeleteStudent.setOnClickListener{ viewModel.deleteStudent(students.value?.get(position)) }
        //holder.itemView.findNavController().navigate(R.id.action_stationFragment_to_valuesFragment)

    }

    override fun getItemCount(): Int {
        return grades.value?.size?:0
    }

}