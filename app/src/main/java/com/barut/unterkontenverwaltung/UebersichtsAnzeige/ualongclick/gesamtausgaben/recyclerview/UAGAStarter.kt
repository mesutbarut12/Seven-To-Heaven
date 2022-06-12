package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.recyclerview.UAGAAdapter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.recyclerview.UAGAModel
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.recyclerview.UaVSAdapter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.recyclerview.UaVSModel


class UAGAStarter(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<UAGAModel>

) {


    init {
        recyclerView.adapter = UAGAAdapter(
            inhhalt
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}