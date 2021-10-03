package com.example.catapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapp.data.Cat


class CatAdapter(val cats: MutableList<Cat>): RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    private var onClickListener: OnImageClickListener? = null
    public lateinit var context:Context
    inner class CatViewHolder(view:View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(p0: View?) {
            onClickListener!!.onImageClick(bindingAdapterPosition, p0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.cat_image)

        Glide
            .with(holder.itemView.context)
            .load(cats[position].url)
            .into(imageView)
        holder.itemView.setOnClickListener(View.OnClickListener {
            holder.onClick(holder.itemView)
        })
    }

    override fun getItemCount(): Int {
        return cats.size
    }
    fun appendCats(cats:MutableList<Cat>){
        this.cats.addAll(cats)
    }

    fun getCatList():MutableList<Cat>{
        return cats
    }

}