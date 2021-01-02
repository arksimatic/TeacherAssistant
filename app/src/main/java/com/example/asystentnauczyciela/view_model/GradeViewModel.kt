package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.data.Database
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.data.repositories.GradeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {

    //we show students of this course
    var choosenSubjectId: Int? = null
    var choosenStudentId: Int? = null

    //all students
    //not sure whether I can use var here
    lateinit var readAllData: LiveData<List<Grade>>

    private val repository: GradeRepository

    fun getNameOfChoosenStudent(): String
    {
        return repository.getNameOfStudent(choosenStudentId!!)
    }

    fun getNameOfChoosenSubject(): String
    {
        return repository.getNameOfSubject(choosenSubjectId!!)
    }

    //we should call this before attaching this to the recycler
    fun initWithStudentAndSubject(studentId: Int, subjectId: Int) {
        choosenSubjectId = subjectId
        choosenStudentId = studentId
        readAllData = repository.getGradesOfStudentOfSubject(choosenStudentId!!, choosenSubjectId!!)
    }


    init {
        val gradeDao = Database.getDatabase(application).gradeDao()
        repository = GradeRepository(gradeDao)
        //readAllDataOfSubject = repository.readAllDataOfSubject()
    }

    fun initWithDate() {
        readAllData = repository.getAllGradesFromToday()
    }

    fun getNameOfStudentOfGrade(gradeId: Int) : String {
        val s : Student = repository.getStudentOfGrade(gradeId)
        return s.firstName + " " + s.lastName
    }

    fun getNameOfSubjectOfGrade(gradeId: Int) : String {
        val s : Subject = repository.getSubjectOfGrade(gradeId)
        return s.name
    }

    fun addGrade(studentId: Int, subjectId: Int, gradeValue: Int, note: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGrade(studentId, subjectId, gradeValue, note)
        }
    }

    fun deleteGrade(grade: Grade?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(grade!=null)
                repository.deleteGrade(grade)
        }
    }

    fun confirmGrade(gradeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.confirmGrade(gradeId)
        }
    }

}