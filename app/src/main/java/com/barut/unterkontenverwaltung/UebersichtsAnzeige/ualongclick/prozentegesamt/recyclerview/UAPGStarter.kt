package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.prozentegesamt.recyclerview.UAPGAdapter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.prozentegesamt.recyclerview.UAPGModel


class UAPGStarter(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<UAPGModel>

) {


    init {
        recyclerView.adapter = UAPGAdapter(
            inhhalt
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}