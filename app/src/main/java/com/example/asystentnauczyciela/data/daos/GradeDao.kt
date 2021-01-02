package com.example.asystentnauczyciela.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.Subject
import java.util.*

@Dao
interface GradeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGrade(grade: Grade)

    @Delete()
    suspend fun deleteGrade(grade: Grade)

    @Query("SELECT * FROM grade_table ORDER BY gradeId")
    fun readAllData(): LiveData<List<Grade>>

    @Query("SELECT * FROM grade_table WHERE studentId=:studentId AND subjectId=:subjectId")
    //@Query("SELECT grade_table.* FROM grade_table INNER JOIN student_table ON grade_table.studentId = student_table.studentId INNER JOIN subject_table ON subject_table.subjectId = grade_table.subjectId WHERE (grade_table.studentId)")
    //@Query("SELECT grade_table.* FROM grade_table INNER JOIN studentSubjectCrossRef_table ON studentSubjectCrossRef_table.studentId = student_table.studentId INNER JOIN subject_table ON subject_table.subjectId = studentSubjectCrossRef_table.subjectId WHERE subject_table.subjectId = :subjectId")
    fun getGradesOfSubjectOfStudent(studentId: Int, subjectId: Int): LiveData<List<Grade>>

    @Query("SELECT * FROM student_table WHERE studentId=:studentId")
    fun getStudentName(studentId: Int) : Student

    @Query("SELECT * FROM subject_table WHERE subjectId=:subjectId")
    fun getSubjectName(subjectId: Int) : Subject


    @Query("DELETE FROM grade_table")
    suspend fun deleteAllGrades()

    @Query("SELECT * FROM grade_table WHERE (date BETWEEN :startDate AND :endDate) AND isConfirmed=:isConfirmed")
    fun getAllGradesFromDate(startDate: Date, endDate: Date, isConfirmed: Boolean) : LiveData<List<Grade>>

    //not sure whether this one works properly
    @Query("SELECT student_table.* FROM student_table INNER JOIN grade_table ON grade_table.studentId = student_table.studentId WHERE gradeId = :gradeId")
    fun getStudentOfGrade(gradeId: Int) : Student

    //same as above
    @Query("SELECT subject_table.* FROM subject_table INNER JOIN grade_table ON grade_table.subjectId = subject_table.subjectId WHERE gradeId = :gradeId")
    fun getSubjectOfGrade(gradeId: Int) : Subject

    @Query("UPDATE grade_table SET isConfirmed=:confirm WHERE gradeId=:gradeId")
    suspend fun confirmGrade(gradeId: Int, confirm: Boolean)
}