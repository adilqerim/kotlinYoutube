package com.kerimov.adee.kotlinyoutubelbta

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: Models.HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {


    //numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        //how do we even create
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row,p0,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val video = homeFeed.videos[p1]
        p0.itemView.textView_video.text = video.name

        p0.itemView.textView_channel.text = video.channel.name + "         " + "20K views\n4 days ago"

        Picasso.get().load(video.imageUrl).into(p0.itemView.imageView_preview)

        Picasso.get().load(video.channel.profileImageUrl).into(p0.itemView.imageView_photo)


    }

}

class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    init {
        v.setOnClickListener{
            val intent = Intent(v.context,CourseDetailActivity::class.java)
            v.context.startActivity(intent)
        }
    }
}