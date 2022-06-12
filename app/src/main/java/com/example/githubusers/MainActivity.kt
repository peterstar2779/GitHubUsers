package com.example.githubusers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var userList : ListView
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = findViewById<ListView>(R.id.userList)
        context = this

        val sb = StringBuffer()
        val apiService = AppClientManager.client.create(ApiService::class.java)
        apiService.index().enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                val list = response.body()
                var index = 1
                if (list != null) {
                    println("list.size = " + list.size)
                }//if

                for (p in list!!) {
                    if(index<list.size) {
                        sb.append(p.login + ",")
                        println("P1 = " + p.login + ",")
                    }else {
                        sb.append(p.login)
                        println("P2 = " + p.login)
                    }
                    index++
                }//for p

                val userArray = sb.toString().split(",")
                for(i in userArray.indices){
                    println(userArray[i])
                }//for i

                val myAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)
                myAdapter.addAll(userArray)

                userList.adapter = myAdapter

            }//onResponse
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
            }//onFailure
        })//enqueue



    }//onCreate

}//MainActivity