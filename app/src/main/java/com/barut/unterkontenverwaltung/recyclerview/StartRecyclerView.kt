package com.barut.unterkontenverwaltung.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface

class StartRecyclerView(
    val context: Context, val recylcerView: RecyclerView
    , val inhaltArrayList: ArrayList<Model>, val layout: Int, holderId: String,
    val showExistingUnterkontoInterface: ShowExistingUnterkontoInterface?,
    val newInhaltArrayList : NewModel?
) {


    init {
        recylcerView.adapter =RecyclerViewAdapterMain(inhaltArrayList,layout,holderId,recylcerView,
        showExistingUnterkontoInterface,newInhaltArrayList)
        recylcerView.layoutManager = LinearLayoutManager(context)

    }



}