package com.barut.unterkontenverwaltung.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface

class StartRecyclerView(
    val context: Context, val recylcerView: RecyclerView
    , val inhaltArrayList: ArrayList<RecylcerViewModel>, val layout: Int, holderId: String,
    val showExistingUnterkontoInterface: ShowExistingUnterkontoInterface?
) {


    init {
        recylcerView.adapter =RecyclerViewAdapterMain(inhaltArrayList,layout,holderId,recylcerView,
        showExistingUnterkontoInterface)
        recylcerView.layoutManager = LinearLayoutManager(context)

    }



}