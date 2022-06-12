package com.barut.unterkontenverwaltung.recyclerview.hauptanzeige

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.ualongclick.UaModel
import com.barut.unterkontenverwaltung.recyclerview.ualongclick.UaRecyclerViewAdapter


class UaStartRecyclerView(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<UaModel>

) {


    init {
        recyclerView.adapter = UaRecyclerViewAdapter(
            inhhalt
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}