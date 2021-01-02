package com.example.asystentnauczyciela.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student (
    @PrimaryKey(autoGenerate=true)
    val studentId: Int,
    val firstName: String,
    val lastName: String
)