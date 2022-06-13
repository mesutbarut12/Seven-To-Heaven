package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UaGSStartRecyclerView(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<UaGSModel>

) {


    init {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = UaGSRecyclerViewAdapter(
            inhhalt
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}