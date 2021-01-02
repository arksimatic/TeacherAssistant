package com.example.asystentnauczyciela.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//@Entity(primaryKeys = ["studentId", "subjectId"])
//data class StudentSubjectCrossRef (
//    val studentId: Int,
//    val subjectId: Int
//)

@Entity(
    tableName = "studentSubjectCrossRef_table",
    foreignKeys = [
        ForeignKey(entity = Student::class, parentColumns = ["studentId"], childColumns = ["studentId"]),
        ForeignKey(entity = Subject::class, parentColumns = ["subjectId"], childColumns = ["subjectId"])
    ]
)
data class StudentSubjectCrossRef(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentId: Int,
    val subjectId: Int
)