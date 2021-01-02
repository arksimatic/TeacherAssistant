package com.example.asystentnauczyciela.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Subject (
    @PrimaryKey(autoGenerate=true)
    val subjectId: Int,
    val name: String
)