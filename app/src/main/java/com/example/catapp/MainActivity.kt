package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Это код для скачивания файла

        /*val filename = url.substringAfterLast("/")
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(filename)
            .setDestinationInExternalFilesDir(context, context.getString(R.string.app_name), filename)
        (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
            .enqueue(request)*/
    }
}