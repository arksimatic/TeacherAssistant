package com.example.asystentnauczyciela.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.data.Database
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.data.repositories.StudentRepository
import com.example.asystentnauczyciela.data.repositories.StudentSubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsOfSubjectViewModel(application: Application) : AndroidViewModel(application) {

    private var _currentStudent: MutableLiveData<Student> = MutableLiveData()
    val currentStudent: LiveData<Student>
        get() = _currentStudent

    var isReverted: Boolean = false

    fun setCurrentStudent(student: Student?) {
        if (student != null)
            _currentStudent.value = student
    }

    fun getNameOfChoosenSubject() : String {
        return repository.returnNameOfSubject(choosenSubjectId!!)
    }

    //we show students of this course
    var choosenSubjectId: Int? = null

    //all students
    //not sure whether I can use var here
    lateinit var readAllData: LiveData<List<Student>>

    private val repository: StudentSubjectRepository

    //we should call this before attaching this to the recycler
    fun initWithSubject(subjectId: Int) {
        isReverted = false
        choosenSubjectId = subjectId
        readAllData = repository.getStudentsOfSubject(choosenSubjectId!!)

    }

    fun demandAllOtherStudents() {
        isReverted = true
        if(choosenSubjectId==null)
            Log.d("SSVM", "choosenSubjectId is null")
        readAllData = repository.getStudentsOfNotSubject(choosenSubjectId!!)
    }


    init {
        val studentSubjectCrossRefDao = Database.getDatabase(
            application
        ).studentSubjectCrossRefDao()
        repository =
            StudentSubjectRepository(
                studentSubjectCrossRefDao
            )
        //readAllDataOfSubject = repository.readAllDataOfSubject()
    }

    fun addStudentToCurrentSubject(student: Student?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(student!=null && choosenSubjectId!=null)
                repository.addCrossRef(student.studentId, choosenSubjectId!!) //null safe?
        }
    }

    fun addCrossRef(studentId: Int, subjectId: Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCrossRef(studentId, subjectId)
        }
    }

    fun deleteStudentFromSubject(student: Student?) {
        if(student!=null) {
            viewModelScope.launch(Dispatchers.IO) {
                if(choosenSubjectId == null) Log.d("ssvm", "choosenSubjectId null!")
                repository.deleteCrossRef(student.studentId, choosenSubjectId!!)
            }
        }
    }
}