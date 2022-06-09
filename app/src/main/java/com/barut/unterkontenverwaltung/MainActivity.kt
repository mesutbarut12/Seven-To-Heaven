package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.barut.unterkontenverwaltung.bottomnavigation.BottomNavigation
import com.barut.unterkontenverwaltung.sqlite.SQliteInit


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        BottomNavigationView()
    }


    fun BottomNavigationView(){
        BottomNavigation().init(findViewById(R.id.bottomNavigation),this)
    }



    fun initAllViews() {
        var sqliteInit = SQliteInit(this)
    }




}