package com.example.asystentnauczyciela.data.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.data.daos.StudentDao
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject

class StudentRepository(private val studentDao: StudentDao) {
    val readAllData: LiveData<List<Student>> = studentDao.readAllData()

    suspend fun addStudent(student: Student) {
        studentDao.addStudent(student)
    }

    suspend fun deleteStudent(student: Student) {
        //studentDao.deleteAllGradesOfStudent(student.studentId)
        studentDao.deleteStudentWithGrades(student.studentId)
    }

    fun getStudentOfId(id: Int): Student? {
        val student: Student? = studentDao.getStudentOfId(id)
        return student
    }

    suspend fun editStudent(student: Student?, newName: String, newLastname: String) {
        if(student!=null)
            studentDao.editStudent(student.studentId, newName, newLastname)
    }

    suspend fun deleteAllStudents() {
        studentDao.deleteAllStudents()
    }

    suspend fun deleteStudentOfId(id: Int) {
        val student: Student? = studentDao.getStudentOfId(id)
        if (student!=null)
            deleteStudent(student)
    }
}