package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.unterkonto

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HALCDUnterkontoStarter(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<HALCDModelUnterkonto>?,
) {


    init {
        val adapter = HALCDAdapterUnterkonto(inhhalt!!)
        val itemTouchHelper = ItemTouchHelper(HALCDItemTouchHelper(inhhalt,context))
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}