package com.example.asystentnauczyciela.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

//not sure
@Entity(tableName = "grade_table",
    foreignKeys = [
    ForeignKey(entity = Student::class, parentColumns = ["studentId"], childColumns = ["studentId"]),
    ForeignKey(entity = Subject::class, parentColumns = ["subjectId"], childColumns = ["subjectId"])
]
)
data class Grade (
    @PrimaryKey(autoGenerate=true)
    val gradeId: Int,
    //@ForeignKey(entity = Student::class, parentColumns = ["studentId"], childColumns = ["studentId"])
    val studentId: Int,
    //@ForeignKey(entity = Student::class, parentColumns = ["subjectId"], childColumns = ["subjectId"])
    val subjectId: Int,
    val value: Int,
    val note: String,
    val date: Date,
    var isConfirmed: Boolean = false
)