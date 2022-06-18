package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferPopUpDelete

class HAEinnahmeStarter(
    private val recyclerView: RecyclerView, private val context: Context,
    val inhalt: ArrayList<HAEinnahmeModel>, val dataTransferPopUpDelete: DataTransferPopUpDelete
) {


    fun init() {

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = HAEinnahmeAdapter(inhalt,dataTransferPopUpDelete)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}