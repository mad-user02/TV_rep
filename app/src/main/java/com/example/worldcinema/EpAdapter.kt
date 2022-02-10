package com.example.worldcinema

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldcinema.model.EModel
import com.example.worldcinema.model.FilmsModel

class EpAdapter (var items : List<EModel>, val context : Activity) :  RecyclerView.Adapter<EpAdapter.Holder>(){
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.card_ep_image)
        val text : TextView = itemView.findViewById(R.id.card_ep_text)
        fun bind(item : EModel) {
            //Glide.with(context).load("http://cinema.areas.su/up/images/" + item.images[0]).into(image)
            text.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return(Holder(LayoutInflater.from(parent.context).inflate(R.layout.card_ep, parent, false)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}