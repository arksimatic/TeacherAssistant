package com.example.asystentnauczyciela.view_model

import android.app.Application
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

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    var choosenSubject: Subject? = null
    val readAllData: LiveData<List<Student>>
        get() {
            //if(choosenSubject==null)
                return repository.readAllData
//            else
//            {
//                var temp = choosenSubject
//                choosenSubject = null
//                return studentSubjectRepository.readAllDataOfSubject(temp)
//            }

        }
    //val readAllDataOfSubject: LiveData<List<Student>>

    private val repository: StudentRepository
    //private val studentSubjectRepository: StudentSubjectRepository

    private var _currentStudentSubjects: MutableLiveData<List<Subject>> = MutableLiveData()
    val currentStudentSubjects: LiveData<List<Subject>>
        get()=_currentStudentSubjects

    private var _currentStudent: MutableLiveData<Student> = MutableLiveData()
    val currentStudent: LiveData<Student>
        get() = _currentStudent

    fun setCurrentStudent(student: Student?) {
        if (student != null)
            _currentStudent.value = student
    }

    fun initWithStudent(studentId: Int) {
        //viewModelScope.launch(Dispatchers.IO) { //possible crash due to coroutine
            _currentStudent.value = repository.getStudentOfId(studentId)
    }



    init {
        val studentDao = Database.getDatabase(
            application
        ).studentDao()
        repository =
            StudentRepository(
                studentDao
            )
        //readAllData = repository.readAllData
        //readAllDataOfSubject = repository.readAllDataOfSubject()
    }

    fun addStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun addStudent(name: String, surname: String){
        val student = Student(0, name, surname)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun deleteStudent(student: Student?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(student!=null)
                repository.deleteStudent(student)
        }
    }

    fun deleteStudentOfId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudentOfId(id)
        }
    }

    fun editStudent(newName: String, newLastname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editStudent(_currentStudent.value, newName, newLastname)
        }
    }
    fun deleteAllStudents() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStudents()
        }
    }

}