package com.example.catapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionSet
import android.widget.Button
import android.widget.ImageView
import androidx.core.transition.doOnEnd

class ImageActivity : AppCompatActivity() {
    val URL_KEY = "URL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val image = findViewById<ImageView>(R.id.image_fullscreen)
        val btnDownload = findViewById<Button>(R.id.btn_download)
        val url = intent.getStringExtra(URL_KEY)?:""

        btnDownload.setOnClickListener{
            downloadCatImageFromNet(url,applicationContext)
        }

        supportPostponeEnterTransition()
        image.load(url){
            supportStartPostponedEnterTransition()
        }
        window.sharedElementEnterTransition = TransitionSet()
            .addTransition(ChangeImageTransform())
            .addTransition(ChangeBounds())
            .apply {
                doOnEnd { image.load(url) }
            }

    }
    fun downloadCatImageFromNet(url: String, context: Context) {
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val fileName = url.substringAfterLast('/').trim()

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("File \"$fileName\"")
            .setDescription("File \"$fileName\" is downloading...")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downLoadId = downloadManager.enqueue(request)
    }
}