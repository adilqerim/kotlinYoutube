package com.kerimov.adee.kotlinyoutubelbta

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_lesson_row.view.*
import kotlinx.android.synthetic.main.video_row.view.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView_main.layoutManager = LinearLayoutManager(this)
   //     recycleView_main.adapter = CourseDetailAdapter()

        //we'll change the nav bar title..

        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)

        supportActionBar?.title = navBarTitle

   //     Toast.makeText(this,courseDetailUrl,Toast.LENGTH_SHORT).show()

        fetchJSON()
    }

    private fun fetchJSON() {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY,-1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$videoId"

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                val courseLessons = gson.fromJson(body,Array<Models.CourseLesson>::class.java)

                runOnUiThread{
                    recycleView_main.adapter = CourseDetailAdapter(courseLessons)
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }

    private class CourseDetailAdapter(val courseLessons: Array<Models.CourseLesson>) : RecyclerView.Adapter<CourseLessonViewHolder>(){
        override fun getItemCount(): Int {
            return courseLessons.count()
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CourseLessonViewHolder {
            val customView = LayoutInflater.from(p0.context).inflate(R.layout.course_lesson_row,p0,false)
            return CourseLessonViewHolder(customView)
        }

        override fun onBindViewHolder(p0: CourseLessonViewHolder, p1: Int) {
            val courseLesson = courseLessons.get(p1)

            p0.customView.textView_course_lesson_title.text = courseLesson.name
            p0.customView.textView_duration.text = courseLesson.duration
            Picasso.get().load(courseLesson.imageUrl).into(p0.customView.imageView)

            p0.courseLesson = courseLesson
        }
    }

    class CourseLessonViewHolder(val customView : View,var courseLesson: Models.CourseLesson? = null): RecyclerView.ViewHolder(customView) {
        companion object {
            val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK"
        }
        init {
            customView.setOnClickListener{
                val intent = Intent(customView.context,CourseLessonActivity::class.java)

                intent.putExtra(COURSE_LESSON_LINK_KEY, courseLesson?.link)
                customView.context.startActivity(intent)
            }
        }
    }
}


