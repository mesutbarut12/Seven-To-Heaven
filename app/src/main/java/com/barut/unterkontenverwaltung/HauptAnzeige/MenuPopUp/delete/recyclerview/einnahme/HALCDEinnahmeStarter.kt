package com.barut.unterkontenverwaltung.HauptAnzeige.delete.recyclerview.unterkonto

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.einnahme.HALCDEinnahmeAdapter
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.einnahme.HALCDEinnahmeModel


class HALCDEinnahmeStarter(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<HALCDEinnahmeModel>?,
) {


    init {
        val adapter = HALCDEinnahmeAdapter(inhhalt!!)
        val itemTouchHelper = ItemTouchHelper(HALCDEinnahmeTouchHelper(inhhalt,context))
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}