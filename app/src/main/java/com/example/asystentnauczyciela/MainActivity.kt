package com.example.asystentnauczyciela

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.data.Database
import com.example.asystentnauczyciela.data.entities.Grade
import com.example.asystentnauczyciela.view_model.StudentViewModel
import com.example.asystentnauczyciela.data.entities.Student
import com.example.asystentnauczyciela.data.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.data.entities.Subject
import com.example.asystentnauczyciela.view_model.GradeViewModel
import com.example.asystentnauczyciela.view_model.StudentsOfSubjectViewModel
import com.example.asystentnauczyciela.view_model.SubjectViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var buttonToSubjectsList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        //first run
        //addRandomDataToDatabase()
        //second run
        //addRandomJunctions()
        //third run
        //addGrades()

        //setupActionBarWithNavController(findNavController(R.id.fragment))
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


    //testing purposes
    private fun addRandomDataToDatabase() {
        val s1: Student = Student(0, "Jan", "Kowalski")
        val s2: Student = Student(0, "Marian", "Nowak")
        val s3: Student = Student(0, "Kamil", "Ślimak")
        val s4: Student = Student(0, "Adam", "Kowal")
        val s5: Student = Student(0, "Maria", "Kamińska")

        val svm: StudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        svm.deleteAllStudents()
        svm.addStudent(s1)
        svm.addStudent(s2)
        svm.addStudent(s3)
        svm.addStudent(s4)
        svm.addStudent(s5)

        val sj1: Subject = Subject(0, "Biology")
        val sj2: Subject = Subject(0, "Chemistry")
        val sj3: Subject = Subject(0, "Maths")
        val sj4: Subject = Subject(0, "English")
        val sj5: Subject = Subject(0, "PE")

        val sjvm: SubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        sjvm.deleteAllSubjects()
        sjvm.addSubject(sj1)
        sjvm.addSubject(sj2)
        sjvm.addSubject(sj3)
        sjvm.addSubject(sj4)
        sjvm.addSubject(sj5)

    }
    fun addRandomJunctions() {
        val sscrvm: StudentsOfSubjectViewModel = ViewModelProvider(this).get(StudentsOfSubjectViewModel::class.java)
        sscrvm.addCrossRef(1, 1)
        sscrvm.addCrossRef(2, 1)
        sscrvm.addCrossRef(2, 2)
        sscrvm.addCrossRef(3, 1)
        sscrvm.addCrossRef(3, 2)
        sscrvm.addCrossRef(3, 3)
        sscrvm.addCrossRef(4, 1)
        sscrvm.addCrossRef(4, 2)
        sscrvm.addCrossRef(4, 3)
        sscrvm.addCrossRef(4, 4)
    }

    fun addGrades() {
        //val time = Calendar.getInstance().time
//        val g1: Grade = Grade(0, 2, 1, "baldzo ladnie", time)// , java.sql.Date(1999, 1, 1))
//        val g2: Grade = Grade(0, 3, 2, "super", time)//, java.sql.Date(2020, 2, 20))
//        val g3: Grade = Grade(0, 1, 5, "aaa", time)
//        val g4: Grade = Grade(0, 2, 1, "aaa", time)
//        val g5: Grade = Grade(0, 3, 2, "aaa", time)
//        val g6: Grade = Grade(0, 3, 3, "aaa", time)
//        val g7: Grade = Grade(0, 1, 4, "aaa", time)
        val gvm: GradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)
        gvm.addGrade(2, 3, 5, "aa")
        gvm.addGrade(2, 3, 2, "aa")
        gvm.addGrade(2, 3, 1, "aa")
        gvm.addGrade(3, 3, 5, "aa")
        gvm.addGrade(4, 5, 1, "aa")
        gvm.addGrade(4, 5, 2, "aa")
        gvm.addGrade(4, 5, 3, "aa")
        gvm.addGrade(4, 5, 4, "aa")
    }


}