package com.barut.unterkontenverwaltung.HauptAnzeige.delete.recyclerview.unterkonto

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.ausgabe.HALCDAdapterAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.ausgabe.HALCDModelAusgabe


class HALCDStarterAusgabe(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<HALCDModelAusgabe>?,
) {


    init {
        val adapter = HALCDAdapterAusgabe(inhhalt!!)
        val itemTouchHelper = ItemTouchHelper(HALCDTouchHelperAusgabe(inhhalt,context))
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}