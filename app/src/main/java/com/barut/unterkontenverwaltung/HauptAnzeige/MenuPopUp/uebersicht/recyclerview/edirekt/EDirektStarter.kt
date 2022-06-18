package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferPopUpDelete
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.edirekt.EDirektAdapter
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.edirekt.EDirektModel

class EDirektStarter(
    private val recyclerView: RecyclerView, private val context: Context,
    val inhalt: ArrayList<EDirektModel>,
    val dataTransferPopUpDelete: DataTransferPopUpDelete
) {


    fun init() {

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = EDirektAdapter(inhalt,dataTransferPopUpDelete)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}