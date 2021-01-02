package com.example.asystentnauczyciela.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.StudentWithSubjects

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: Student)

    @Delete()
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student_table WHERE studentId=:studentId")
    fun deleteStudent(studentId: Int)

    @Query("UPDATE student_table SET firstName=:firstName, lastName=:lastName WHERE studentId=:studentId")
    suspend fun updateStudent(studentId: Int, firstName: String, lastName: String)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudents()

    @Query("SELECT COUNT(studentId) FROM student_table")
    fun getStudentCount(): LiveData<Int> //is this correct?

    @Query("SELECT * FROM student_table ORDER BY studentId")
    fun readAllData(): LiveData<List<Student>>

//    @Transaction
//    @Query("SELECT * FROM student_table")
//    fun getStudentsWithSubjects(): List<StudentWithSubjects>
    @Query("UPDATE student_table SET firstName =:newName, lastName =:newLastname WHERE studentId=:studentId")
    fun editStudent(studentId: Int, newName: String, newLastname: String)

    @Query("SELECT * FROM student_table WHERE studentId = :wantedId")
    fun getStudentOfId(wantedId: Int): Student

    @Query("DELETE FROM grade_table WHERE studentId=:studentId")
    fun deleteGradesOfStudent(studentId: Int)

    @Query("DELETE FROM studentSubjectCrossRef_table WHERE studentId=:studentId")
    fun deleteSubjectsOfStudent(studentId: Int)

    @Transaction
    fun deleteStudentWithGrades(studentId: Int) {
        deleteGradesOfStudent(studentId)
        deleteSubjectsOfStudent(studentId)
        deleteStudent(studentId)
    }

}