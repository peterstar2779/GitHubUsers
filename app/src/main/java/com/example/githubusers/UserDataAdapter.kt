package com.example.githubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserDataAdapter(private val mData: List<String>) : RecyclerView.Adapter<ViewHolderUser>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        val v = LayoutInflater.from(parent.context).inflate(com.example.githubusers.R.layout.item, parent, false)
        return ViewHolderUser(v)
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        holder.dataView.text = mData[position]
        //Store user detail items figure.
        if(position == 0) holder.img.setImageResource(R.drawable.userlogo2)
        else if(position == 1) holder.img.setImageResource(R.drawable.location)
        else if(position == 2) holder.img.setImageResource(R.drawable.link)
    }//onBindViewHolder

    override fun getItemCount(): Int {
        return mData.size
    }
}

class ViewHolderUser(v: View) : RecyclerView.ViewHolder(v) {
    val dataView: TextView = v.findViewById(com.example.githubusers.R.id.info_text)
    val img : ImageView = v.findViewById(com.example.githubusers.R.id.img)
}