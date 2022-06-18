package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.ausgabe

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HALCDHolderAusgabe(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val datum : TextView = itemView.findViewById(R.id.item_delete_a_datum)
    val summe : TextView = itemView.findViewById(R.id.item_delete_a_summe)
    val name : TextView = itemView.findViewById(R.id.item_delete_a_name)
}