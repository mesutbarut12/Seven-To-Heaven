package com.barut.unterkontenverwaltung.recyclerview

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.barut.unterkontenverwaltung.R

class RecyclerViewBinder(val holder : RecyclerViewHolderMain,val id : String,val inhalt : ArrayList<RecylcerViewModel>) {

    fun onStart() {
        if (holder.differntHolder() != null) {
            if (id == "ShowItems") {
                val showlist = holder.differntHolder()
                val image = showlist!!.get(0) as ImageView
                val tvspaltenname1 = showlist!!.get(1) as TextView
                val tvspaltenname2 = showlist!!.get(2) as TextView
                val echtzeitDatum = showlist!!.get(3) as TextView
                image.setImageResource(R.drawable.ic_money)
                tvspaltenname1.setText(inhalt.get(holder.adapterPosition).spaltenName1Inhalt)
                tvspaltenname2.setText(inhalt.get(holder.adapterPosition).spaltenName2Inhalt)
                echtzeitDatum.setText(inhalt.get(holder.adapterPosition).date)
            }
        } else {
            Toast.makeText(holder.itemView.context,"Fehler beim Laden",Toast.LENGTH_LONG).show()
        }
    }
}