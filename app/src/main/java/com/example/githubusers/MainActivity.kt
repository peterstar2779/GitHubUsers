package com.example.githubusers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubusers.Config.mainActivityContext
import com.example.githubusers.ui.UserDetailFragment
import com.example.githubusers.ui.UserListFragment


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_f)

        mainActivityContext = this

        if (savedInstanceState == null) {
            switchFragment(1) //First Fragment
        }//if

    }//onCreate

    fun switchFragment(id : Int){

        val transaction = supportFragmentManager.beginTransaction()

        if(id==1){
            transaction.replace(R.id.main_fragment_layout, UserListFragment())
            transaction.commit()
        }else if(id==2){
            transaction.replace(R.id.main_fragment_layout, UserDetailFragment())
            transaction.commit()
        }//else if

    }//switchFragment

}//MainActivity

