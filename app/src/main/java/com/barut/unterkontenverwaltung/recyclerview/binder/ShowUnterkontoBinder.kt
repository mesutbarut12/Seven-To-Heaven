package com.barut.unterkontenverwaltung.showexistingunterkonten

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferShowUnterkontoBinder
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel
import com.barut.unterkontenverwaltung.sqlite.SQliteInit

class ShowExistingUnterkontenInRecyclerViewBinder(
    val holder: RecyclerViewHolderMain, val id: String,
    val inhalt: ArrayList<UnterkontoModel>,
    val recyclerView: RecyclerView,
    val dataTransferShowUnterkontoBinder: DataTransferShowUnterkontoBinder?
) {

    val showlist = holder.differntHolder()
    val tv = showlist!!.get(0) as TextView
    val tvGuthaben = showlist!!.get(1) as TextView
    val sQliteInit = SQliteInit(holder.itemView.context)


    fun init() {
        if (holder.differntHolder() != null) {
            if (id == "ShowExistingUnterkontenItems") {
                tv.setText(inhalt.get(holder.adapterPosition).name)
                clickUnterkonto()

            }
        }
    }

    fun clickUnterkonto() {

        holder.itemView.setOnClickListener {
            dataTransferShowUnterkontoBinder!!.getData(inhalt.get(holder.adapterPosition).name)
        }

    }


}

