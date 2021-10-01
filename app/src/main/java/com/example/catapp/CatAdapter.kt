package com.example.catapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapp.data.Cat
import com.example.catapp.databinding.CatItemBinding


class CatAdapter(val cats:List<Cat>): RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    private var onClickListener: OnImageClickListener? = null
    inner class CatViewHolder(val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(cat: Cat){
            binding.cat = cat
        }

        override fun onClick(p0: View?) {
            onClickListener!!.onImageClick(bindingAdapterPosition, p0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = CatItemBinding.inflate(inflater, parent, false)
        return CatViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats[position])
        val imageView = holder.itemView.findViewById<ImageView>(R.id.image_fullscreen)
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

}