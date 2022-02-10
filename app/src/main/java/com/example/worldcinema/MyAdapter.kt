package com.example.worldcinema

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldcinema.model.FilmsModel

class MyAdapter (var items : List<FilmsModel>, val context : Activity) :  RecyclerView.Adapter<MyAdapter.Holder>(){
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.card_films_image)
        val text : TextView = itemView.findViewById(R.id.card_text)
        fun bind(item : FilmsModel) {
            Glide.with(context).load("http://cinema.areas.su/up/images/" + item.poster).into(image)
            text.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return(Holder(LayoutInflater.from(parent.context).inflate(R.layout.card_films, parent, false)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}