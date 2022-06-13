package com.example.githubusers.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.*
import com.example.githubusers.Config.userName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.util.concurrent.Executors


class UserDetailFragment() : Fragment() {


    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater!!.inflate(R.layout.userdetail_fragment, container, false)
        val previous : Button = view.findViewById(R.id.previous)
        val avatarImage : ImageView = view.findViewById(R.id.avatarImage)
        val userNameTextView : TextView = view.findViewById(R.id.userNameTextView)
        previous.setOnClickListener(){
            (activity as MainActivity).switchFragment(1)
        }//Previous
        val apiService = AppClientManager.client.create(ApiService::class.java)
        apiService.getUser(userName).enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                val list = response.body()
                val listData = ArrayList<String>()
                if (list != null) {
                    //Get User data (avatar, name, twitter name, location, blog)
                    var bmp: Bitmap
                    val handler = Handler(Looper.getMainLooper())
                    val executor = Executors.newSingleThreadExecutor()
                    executor.execute {
                        val imageURL = "${list.avatarUrl}"
                        try {
                            val inb = URL(imageURL).openStream()
                            bmp = BitmapFactory.decodeStream(inb)
                            // Only for making changes in UI
                            handler.post {
                                avatarImage.setImageBitmap(bmp)
                            }
                        }//try
                        catch (e: Exception) {
                            e.printStackTrace()
                        }//catch
                    }//executor
                    userNameTextView.setText("${list.twitterUsername}")
                    listData.add("${list.login}")
                    listData.add("${list.location}")
                    listData.add("${list.blog}")
                }

                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                val dataList = view.findViewById<RecyclerView>(R.id.user_detail)
                dataList.layoutManager = layoutManager
                dataList.adapter = UserDataAdapter(listData)

            }//onResponse
            override fun onFailure(call: Call<Posts>, t: Throwable) {
                println("User Detail onFailure t = $t")
            }//onFailure
        })//enqueue
        return view
    }//onCreateView

}//UserDetailFragment