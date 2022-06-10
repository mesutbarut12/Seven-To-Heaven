package com.barut.unterkontenverwaltung.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferShowUnterkontoBinder
import java.util.ArrayList

class StartRecyclerView(
    val context: Context,
    val recylcerView: RecyclerView,
    val inhaltArrayList: ArrayList<UnterkontoModel>,
    val layout: Int,
    holderId: String,
    val newInhaltArrayList: NewModel?,
    val dataTransferShowUnterkontoBinder: DataTransferShowUnterkontoBinder?
) {


    init {
        recylcerView.adapter = RecyclerViewAdapterMain(
            inhaltArrayList, layout, holderId, recylcerView,
            newInhaltArrayList, dataTransferShowUnterkontoBinder
        )
        recylcerView.layoutManager = LinearLayoutManager(context)

    }


}