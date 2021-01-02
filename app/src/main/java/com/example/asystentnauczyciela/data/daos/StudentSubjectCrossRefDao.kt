package com.example.asystentnauczyciela.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.data.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject

@Dao
interface StudentSubjectCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudentSubjectCrossRef(studentSubjectCrossRef: StudentSubjectCrossRef)

    @Delete()
    suspend fun deleteStudentSubjectCrossRef(studentSubjectCrossRef: StudentSubjectCrossRef)

    @Query("SELECT * FROM studentSubjectCrossRef_table")
    fun readAllData(): LiveData<List<StudentSubjectCrossRef>>

    @Query("SELECT student_table.* FROM student_table INNER JOIN studentSubjectCrossRef_table ON studentSubjectCrossRef_table.studentId = student_table.studentId INNER JOIN subject_table ON subject_table.subjectId = studentSubjectCrossRef_table.subjectId WHERE subject_table.subjectId = :subjectId")
    fun getStudentsFromSubject(subjectId: Int): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE studentId NOT IN (SELECT student_table.studentId FROM student_table INNER JOIN studentSubjectCrossRef_table ON studentSubjectCrossRef_table.studentId = student_table.studentId INNER JOIN subject_table ON subject_table.subjectId = studentSubjectCrossRef_table.subjectId WHERE subject_table.subjectId = :subjectId)")
    fun getNotStudentsFromSubject(subjectId: Int): LiveData<List<Student>>

    @Query("SELECT subject_table.* FROM subject_table INNER JOIN studentSubjectCrossRef_table ON studentSubjectCrossRef_table.subjectId = subject_table.subjectId INNER JOIN student_table ON student_table.studentId = studentSubjectCrossRef_table.studentId WHERE student_table.studentId = :studentId")
    fun getSubjectsFromStudent(studentId: Int): LiveData<List<Subject>>

    @Query("SELECT subject_table.* FROM subject_table INNER JOIN studentSubjectCrossRef_table ON studentSubjectCrossRef_table.subjectId = subject_table.subjectId INNER JOIN student_table ON student_table.studentId = studentSubjectCrossRef_table.studentId WHERE NOT student_table.studentId = :studentId")
    fun getNotSubjectsFromStudent(studentId: Int): LiveData<List<Subject>>

    @Query("SELECT * FROM subject_table WHERE subjectId=:subjectId")
    fun getSubject(subjectId: Int) : Subject

    @Query("DELETE FROM studentSubjectCrossRef_table WHERE studentId=:studentId AND subjectId=:subjectId")
    fun deleteCrossRef(studentId: Int, subjectId: Int)

}