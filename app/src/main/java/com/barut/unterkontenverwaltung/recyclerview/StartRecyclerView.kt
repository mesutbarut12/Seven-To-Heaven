package com.barut.unterkontenverwaltung.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartRecyclerView(val context : Context,val recylcerView : RecyclerView
,val inhaltArrayList : ArrayList<RecylcerViewModel>,val layout : Int) {


    init {
        recylcerView.adapter =RecyclerViewAdapterMain(inhaltArrayList,layout,"Test")
        recylcerView.layoutManager = LinearLayoutManager(context)

    }



}