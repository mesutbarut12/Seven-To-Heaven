package com.barut.unterkontenverwaltung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewAdapterMain
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val inhalt = arrayListOf<RecylcerViewModel>(RecylcerViewModel("Hallo","Test"))
        StartRecyclerView(this,findViewById(R.id.recyclerView),
        inhalt, R.layout.test_layout)



    }
}