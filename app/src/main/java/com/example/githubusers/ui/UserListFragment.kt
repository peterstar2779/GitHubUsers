package com.example.githubusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class UserListFragment : Fragment() {



    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater!!.inflate(R.layout.userlist_fragment, container, false)

        val sb = StringBuffer()
        val apiService = AppClientManager.client.create(ApiService::class.java)
        apiService.index().enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                val list = response.body()
                var index = 1

                val listData = ArrayList<String>()
                for (p in list!!) {
                    if(index<list.size) {
                        sb.append(p.login + ",")
                    }else {
                        sb.append(p.login)
                    }//else
                    index++
                }//for p

                val userArray = sb.toString().split(",")
                for(i in userArray.indices){
                    listData.add(userArray[i])
                }//for i

                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                val dataList = view.findViewById<RecyclerView>(R.id.user_list)
                dataList.layoutManager = layoutManager
                dataList.adapter = DataAdapter(listData)

            }//onResponse
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                println("User List onFailure t =  $t")
            }//onFailure
        })//enqueue
        return view
    }//onCreateView

}//UserListFragment