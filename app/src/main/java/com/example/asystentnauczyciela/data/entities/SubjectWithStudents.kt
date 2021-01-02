package com.example.asystentnauczyciela.data.entities

import androidx.room.*
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.data.entities.Subject

data class SubjectWithStudents (
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "studentId",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )

    val students: List<Student>

)