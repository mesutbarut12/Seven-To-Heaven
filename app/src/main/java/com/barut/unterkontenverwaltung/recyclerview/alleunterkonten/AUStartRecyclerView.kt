package com.barut.unterkontenverwaltung.recyclerview.alleunterkonten

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferShowUnterkontoBinder

class AUStartRecyclerView(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt: AURecyclerViewModel,
    private val dataTransferShowUnterkontoBinder: DataTransferShowUnterkontoBinder
) {


    init {
        recyclerView.adapter = AURecyclerViewAdapter(
            inhhalt,dataTransferShowUnterkontoBinder
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}