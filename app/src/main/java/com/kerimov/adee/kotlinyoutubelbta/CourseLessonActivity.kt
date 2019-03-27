package com.kerimov.adee.kotlinyoutubelbta

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_course_lesson.*

class CourseLessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_course_lesson)

    //    webview_course_lesson.setBackgroundColor(Color.BLUE)

        val courseLink = intent.getStringExtra(CourseDetailActivity.CourseLessonViewHolder.COURSE_LESSON_LINK_KEY)

        webview_course_lesson.loadUrl(courseLink)
    }
}