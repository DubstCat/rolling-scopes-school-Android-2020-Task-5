package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment, ListFragment::class.java, null)
            .commit()

    }

    fun openFullscreenFragment(byteArray: ByteArray){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, ImageFragment.newInstance(byteArray))
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out)
            .commit()
    }
}