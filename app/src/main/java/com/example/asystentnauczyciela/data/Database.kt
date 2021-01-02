package com.example.asystentnauczyciela.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.asystentnauczyciela.data.daos.GradeDao
import com.example.asystentnauczyciela.data.daos.StudentDao
import com.example.asystentnauczyciela.data.daos.StudentSubjectCrossRefDao
import com.example.asystentnauczyciela.data.daos.SubjectDao
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.model.DataConverter
import kotlinx.android.synthetic.main.fragment_student_add.view.*

@androidx.room.Database(
    entities = [
        Student::class,
        Subject::class,
        Grade::class,
        StudentSubjectCrossRef::class
    ],
    version = 12,
    exportSchema = true //false?
)

@TypeConverters(DataConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun gradeDao(): GradeDao
    abstract fun studentSubjectCrossRefDao(): StudentSubjectCrossRefDao

    companion object{
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}