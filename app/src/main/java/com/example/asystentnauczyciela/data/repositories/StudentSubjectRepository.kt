package com.example.asystentnauczyciela.data.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.data.daos.StudentDao
import com.example.asystentnauczyciela.data.daos.StudentSubjectCrossRefDao
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.data.entities.Subject

class StudentSubjectRepository(private val studentSubjectCrossRefDao: StudentSubjectCrossRefDao) {

    fun readAllDataOfSubject(subject: Subject)
    {
        //return studentSubjectCrossRefDao.getStudentsFromSubject(subject.subjectId)
    }

    fun getStudentsOfSubject(subjectId: Int) : LiveData<List<Student>>
    {
        return studentSubjectCrossRefDao.getStudentsFromSubject(subjectId)
    }

    fun getStudentsOfNotSubject(subjectId: Int) : LiveData<List<Student>>
    {
//        val allStudents = studentSubjectCrossRefDao.getAllStudents().toMutableList()
//        val studentsInSubject = studentSubjectCrossRefDao.getListStudentsFromSubject(subjectId)
////        val iterator = studentsInSubject.iterator()
////        iterator.forEach {
////            allStudents.remove
////        }
//        allStudents.removeAll(studentsInSubject)
//        return LiveData<allStudents>
//



        return studentSubjectCrossRefDao.getNotStudentsFromSubject(subjectId)
    }

    fun getNotStudentsOfSubject(subjectId: Int) : LiveData<List<Student>>
    {
        return studentSubjectCrossRefDao.getNotStudentsFromSubject(subjectId)
    }

    fun getSubjectsOfStudent(student: Student) : LiveData<List<Subject>>
    {
        return studentSubjectCrossRefDao.getSubjectsFromStudent(student.studentId)
    }

    fun getNotSubjectsOfStudent(student: Student) : LiveData<List<Subject>>
    {
        return studentSubjectCrossRefDao.getNotSubjectsFromStudent(student.studentId)
    }

    fun returnNameOfSubject(subjectId: Int) : String {
        return studentSubjectCrossRefDao.getSubject(subjectId).name
    }

    suspend fun deleteCrossRef(studentId: Int, subjectId: Int) {
        studentSubjectCrossRefDao.deleteCrossRef(studentId, subjectId)
    }

    suspend fun addCrossRef(studentId: Int, subjectId: Int){
        val studentSubjectCrossRef = StudentSubjectCrossRef(0, studentId, subjectId)
        studentSubjectCrossRefDao.addStudentSubjectCrossRef(studentSubjectCrossRef)
    }
}