package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.popup.recyclerview.einnahme

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe.HAAusgabeAdapter
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe.HAAusgabeModel

class HAAusgabeStarter(
    private val recyclerView: RecyclerView, private val context: Context,
    val inhalt: ArrayList<HAAusgabeModel>
) {


    fun init() {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = HAAusgabeAdapter(inhalt!!)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}