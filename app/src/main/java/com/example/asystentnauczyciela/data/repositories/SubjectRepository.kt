package com.example.asystentnauczyciela.data.repositories

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.asystentnauczyciela.data.daos.SubjectDao
import com.example.asystentnauczyciela.data.entities.Subject

class SubjectRepository(private val subjectDao: SubjectDao) {
    val readAllData: LiveData<List<Subject>> = subjectDao.readAllData()

    suspend fun addSubject(subject: Subject) {
        subjectDao.addSubject(subject)
    }

    suspend fun deleteSubject(subject: Subject) {
        subjectDao.deleteSubjectWithAll(subject.subjectId)
    }

    suspend fun deleteAllSubjects() {
        subjectDao.deleteAllSubjects()
    }

    fun getNameOfSubject(subjectId: Int) : String {
        return subjectDao.getSubjectName(subjectId).name
    }

}