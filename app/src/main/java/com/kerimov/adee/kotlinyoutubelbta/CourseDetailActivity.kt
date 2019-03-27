package com.kerimov.adee.kotlinyoutubelbta

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class CourseDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView_main.layoutManager = LinearLayoutManager(this)
        recycleView_main.adapter = CourseDetailAdapter()
    }

    private class CourseDetailAdapter : RecyclerView.Adapter<CourseLessonViewHolder>(){
        override fun getItemCount(): Int {
            return 5
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CourseLessonViewHolder {
            val customView = LayoutInflater.from(p0.context).inflate(R.layout.course_lesson_row,p0,false)
//            val blueView = View(p0.context)
//            blueView.setBackgroundColor(Color.BLUE)
//            blueView.minimumHeight = 50
            return CourseLessonViewHolder(customView)
        }

        override fun onBindViewHolder(p0: CourseLessonViewHolder, p1: Int) {

        }
    }

    private class CourseLessonViewHolder(val customView : View): RecyclerView.ViewHolder(customView) {

    }
}


