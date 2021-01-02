package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.data.Database
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.data.repositories.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Subject>>
    private val repository: SubjectRepository


    private var _currentSubjectStudents: MutableLiveData<List<Student>> = MutableLiveData()
    val currentSubjectStudents: LiveData<List<Student>>
        get()=_currentSubjectStudents

    private var _currentSubject: MutableLiveData<Subject> = MutableLiveData()
    val currentSubject: LiveData<Subject>
        get() = _currentSubject

    fun setCurrentSubject(subject: Subject?) {
        if (subject != null)
            _currentSubject.value = subject
    }

    fun getCurrentSubjectName() : String {
        return currentSubject.value?.name!!
    }



    init {
        val subjectDao = Database.getDatabase(
            application
        ).subjectDao()
        repository =
            SubjectRepository(
                subjectDao
            )
        readAllData = repository.readAllData
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(subject)
        }
    }

    fun addSubject(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(Subject(0, name))
        }
    }

    fun deleteSubject(subject: Subject?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(subject!=null)
                repository.deleteSubject(subject)
        }
    }

    fun deleteAllSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSubjects()
        }
    }
}