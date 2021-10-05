package com.example.catapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapp.data.Cat


class CatAdapter(val cats: MutableList<Cat>,
                 val context: Context,
                 val activity:Activity)
    : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    inner class CatViewHolder(view:View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var imageView:View ?=null
        fun setImage(img:View){
            imageView=img
        }
        override fun onClick(v: View?) {
            val intent = Intent(context, ImageActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView!!, ViewCompat.getTransitionName(imageView!!)!!)
            intent.putExtra("URL",cats[bindingAdapterPosition].url)
            context.startActivity(intent, options.toBundle())

        }
    init {
        itemView.setOnClickListener(this)
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.cat_image)
        imageView.load(cats[position].url?:"")
        holder.setImage(imageView)

    }
    override fun getItemCount(): Int = cats.size
}