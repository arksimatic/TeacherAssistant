package com.example.asystentnauczyciela.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.data.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.data.entities.StudentWithSubjects
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.data.entities.SubjectWithStudents

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: Subject)

    @Delete()
    suspend fun deleteSubject(subject: Subject)

    @Query("DELETE FROM subject_table WHERE subjectId=:subjectId")
    fun deleteSubject(subjectId: Int)

    @Query("SELECT * FROM subject_table")// ORDER BY subjectId")
    fun readAllData(): LiveData<List<Subject>>

    @Query("UPDATE subject_table SET name=:name WHERE subjectId=:subjectId")
    suspend fun updateSubject(subjectId: Int, name: String)

    @Query("DELETE FROM subject_table")
    suspend fun deleteAllSubjects()

    @Query("SELECT COUNT(subjectId) FROM subject_table")
    fun getSubjectCount(): LiveData<Int> //is this correct?

    @Query("SELECT * FROM subject_table WHERE subjectId = :wantedId")
    fun getSubjectOfId(wantedId: Int): Subject

    @Query("SELECT subject_table.* FROM subject_table INNER JOIN studentSubjectCrossRef_table ON studentSubjectCrossRef_table.subjectId = subject_table.subjectId INNER JOIN student_table ON student_table.studentId = studentSubjectCrossRef_table.studentId WHERE student_table.studentId = :studentId")
    fun getSubjectsFromStudent(studentId: Int): LiveData<List<Subject>>

    @Query("SELECT * FROM studentSubjectCrossRef_table WHERE studentId = :studentId")
    fun getSubjectsFromStudent2(studentId: Int): LiveData<List<StudentSubjectCrossRef>>

    @Query("SELECT * FROM subject_table WHERE subjectId=:subjectId")
    fun getSubjectName(subjectId: Int) : Subject

    @Query("DELETE FROM studentSubjectCrossRef_table WHERE subjectId=:subjectId")
    fun deleteCrossRefWithStudents(subjectId: Int)

    @Query("DELETE FROM grade_table WHERE subjectId=:subjectId")
    fun deleteGradesFromSubject(subjectId: Int)

    @Transaction
    fun deleteSubjectWithAll(subjectId: Int) {
        deleteGradesFromSubject(subjectId)
        deleteCrossRefWithStudents(subjectId)
        deleteSubject(subjectId)
    }
}