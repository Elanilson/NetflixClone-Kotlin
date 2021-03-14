package com.apkdoandroid.netflixclonekotlin

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.apkdoandroid.netflixclonekotlin.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val videourl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-kotlin.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=0ee062b6-c39b-4ae8-b504-5bc2324f94d6")
        val video =  binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(videourl)
        video.requestFocus()
        video.start()
    }
}