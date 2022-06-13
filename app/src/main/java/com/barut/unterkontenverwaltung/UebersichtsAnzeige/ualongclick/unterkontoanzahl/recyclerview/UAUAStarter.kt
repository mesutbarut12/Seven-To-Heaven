package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.unterkontoanzahl.recyclerview.UAUAAdapter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.unterkontoanzahl.recyclerview.UAUAModel


class UAUAStarter(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : ArrayList<UAUAModel>

) {


    init {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = UAUAAdapter(
            inhhalt
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}