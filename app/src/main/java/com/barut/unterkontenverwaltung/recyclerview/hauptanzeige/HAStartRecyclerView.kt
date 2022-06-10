package com.barut.unterkontenverwaltung.recyclerview.hauptanzeige

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R


class HAStartRecyclerView(
    private val recyclerView: RecyclerView,
    private val context: Context,
    private val inhhalt : HAHauptAnzeigeModel

) {


    init {
        recyclerView.adapter = HARecyclerViewAdapterMain(
            inhhalt, R.layout.end_model_show_datas_in_recyclerview
        )
        recyclerView.layoutManager = LinearLayoutManager(context)

    }


}