package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferPopUpDelete
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.unterkonto.HAUnterkontoAdapter
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.unterkonto.HAUnterkontoModel

class HAUnterkontoStarter1(
    private val recyclerView: RecyclerView, private val context: Context,
    val inhalt: ArrayList<HAUnterkontoModel>,
    val dataTransferPopUpDelete: DataTransferPopUpDelete
) {


    fun init() {

        println("Starter")
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = HAUnterkontoAdapter(inhalt,dataTransferPopUpDelete)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}