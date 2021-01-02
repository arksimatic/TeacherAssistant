//package com.example.asystentnauczyciela.fragments.list
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.RecyclerView
//import com.example.asystentnauczyciela.R
//import com.example.asystentnauczyciela.data.entities.Student
//import com.example.asystentnauczyciela.view_model.StudentViewModel
//import kotlinx.android.synthetic.main.custom_row.view.*
//
//class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>()  {
//
//    private var studentList:  MutableList<Student> = mutableListOf<Student>()
//    //private var studentList = emptyList<Student>()
//
//    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return MyViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.custom_row,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return studentList.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = studentList[position]
//        holder.itemView.id_txt.text = currentItem.studentId.toString()
//        holder.itemView.name_txt.text = currentItem.firstName
//        holder.itemView.surname_txt.text = currentItem.lastName
//
//        holder.itemView.deleteStudentButton.setOnClickListener {
//            if(onClickListener != null){
//                Log.d("in ListAdapter" ,"I'm deleting")
//                //onClickListener!!.onClick(position)
//                studentList.removeAt(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position ,studentList.size);
//            }
//        }
//    }
//
//    fun setData(student: List<Student>){
//        this.studentList = student.toMutableList()
//        notifyDataSetChanged()
//    }
//
//    //those for delete button
//    private var onClickListener : OnClickListener? = null
//    fun setOnClickListener(onClickListener: OnClickListener){
//        this.onClickListener = onClickListener
//    }
//    interface OnClickListener {
//        fun onClick(position: Int){
//        }
//    }
//}