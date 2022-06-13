package com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HAStarter(private val recyclerView: RecyclerView,private val context: Context,
val inhalt : ArrayList<HAModel>) {


    fun init(){
        recyclerView.adapter = HAAdapter(inhalt)
        recyclerView.layoutManager = LinearLayoutManager(context)
        println(inhalt)
    }
}