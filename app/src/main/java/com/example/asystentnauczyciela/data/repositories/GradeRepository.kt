package com.example.asystentnauczyciela.data.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.data.daos.GradeDao
import com.example.asystentnauczyciela.data.daos.StudentDao
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject
import java.util.*

class GradeRepository(private val gradeDao: GradeDao) {
    val readAllData: LiveData<List<Grade>> = gradeDao.readAllData()

    suspend fun addGrade(grade: Grade) {
        gradeDao.addGrade(grade)
    }

    suspend fun addGrade(studentId: Int, subjectId: Int, gradeValue: Int, note: String)
    {
        gradeDao.addGrade(Grade(0, studentId, subjectId, gradeValue, note, Calendar.getInstance().time))
    }

    suspend fun deleteGrade(grade: Grade) {
        gradeDao.deleteGrade(grade)
    }

    suspend fun deleteAllGrades() {
        gradeDao.deleteAllGrades()
    }

    fun getGradesOfStudentOfSubject(studentId: Int, subjectId: Int) : LiveData<List<Grade>> {
        return gradeDao.getGradesOfSubjectOfStudent(studentId, subjectId)
    }

    fun getNameOfStudent(studentId: Int) : String {
        val student: Student = gradeDao.getStudentName(studentId)
        return student.firstName + " " + student.lastName
    }

    fun getNameOfSubject(subjectId: Int) : String {
        return gradeDao.getSubjectName(subjectId).name
    }

    fun getAllGradesFromToday() : LiveData<List<Grade>> {
        val today = Calendar.getInstance()
        today.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 0, 0, 0)
        val tomorrow = Calendar.getInstance()
        tomorrow.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 0, 0, 0)
        tomorrow.add(Calendar.DATE, 1)
        val notConfirmed: Boolean = false
        return gradeDao.getAllGradesFromDate(today.time, tomorrow.time, notConfirmed)
    }

    fun getStudentOfGrade(gradeId: Int) : Student {
        return gradeDao.getStudentOfGrade(gradeId)
    }

    fun getSubjectOfGrade(gradeId: Int) : Subject {
        return gradeDao.getSubjectOfGrade(gradeId)
    }

    suspend fun confirmGrade(gradeId: Int) {
        gradeDao.confirmGrade(gradeId, true)
    }

}