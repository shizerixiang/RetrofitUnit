package com.beviswang.retrofitunit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.beviswang.retrofitlib.model.SatinModel
import com.beviswang.retrofitunit.PlayerActivity
import com.beviswang.retrofitunit.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_satin_list.view.*

class SatinListAdapter(private val context: Context, private val satins: ArrayList<SatinModel>)
    : RecyclerView.Adapter<SatinListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_satin_list, null))

    override fun getItemCount(): Int {
        return satins.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val satin = satins[position]
        Glide.with(context).load(satin.imageUri).into(holder.cover)
        holder.title.text = satin.name
        holder.content.text = satin.text
        holder.cover.setOnClickListener {
            context.startActivity(Intent(context, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.VIDEO_URL, satins[holder.adapterPosition].videoUri)
                    .putExtra(PlayerActivity.VIDEO_TITLE, satins[holder.adapterPosition].name))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cover: ImageView = itemView.cover
        var title: TextView = itemView.title
        var content: TextView = itemView.content
    }
}