package com.example.githubusers.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.MainActivity
import com.example.githubusers.R
import com.example.githubusers.mode.Config
import java.net.URL
import java.util.concurrent.Executors

class DataAdapter(private val mData: List<String>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mDataArray = mData[position].split(",")//mDataArray[0]=>text, mDataArray[1]=>image
        holder.dataView.text = mDataArray[0]
        var bmp: Bitmap
        val handler = Handler(Looper.getMainLooper())
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val imageURL = "${mDataArray[1]}"
            try {
                val inb = URL(imageURL).openStream()
                bmp = BitmapFactory.decodeStream(inb)
                // Only for making changes in UI
                handler.post {
                    holder.img.setImageBitmap(bmp)
                }
            }//try
            catch (e: Exception) {
                e.printStackTrace()
            }//catch
        }//executor
        holder.itemView.setOnClickListener {
//            Toast.makeText(it.context, "Item $position is clicked. ${mData[position]}", Toast.LENGTH_SHORT).show()
            println("text data is ${mData[position]}")
            Config.userName = mDataArray[0]
            (Config.mainActivityContext as MainActivity).switchFragment(2)
        }
        holder.itemView.setOnLongClickListener {
//            Toast.makeText(it.context, "Item $position is long clicked.", Toast.LENGTH_SHORT).show()
            println("text data is ${mData[position]}")
            Config.userName = mDataArray[0]
            (Config.mainActivityContext as MainActivity).switchFragment(2)
            true
        }
    }//onBindViewHolder

    override fun getItemCount(): Int {
        return mData.size
    }
}

class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val dataView: TextView = v.findViewById(R.id.info_text)
    val img : ImageView = v.findViewById(R.id.img)
}