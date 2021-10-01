package com.example.catapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageActivity : AppCompatActivity(){
    val IMAGE_URI = "IMAGE_URI"
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val imageUri = Uri.parse(savedInstanceState?.getString(IMAGE_URI))
        imageView = findViewById(R.id.image_fullscreen)
        Glide
            .with(this)
            .load(imageUri)
            .into(imageView)
    }

}