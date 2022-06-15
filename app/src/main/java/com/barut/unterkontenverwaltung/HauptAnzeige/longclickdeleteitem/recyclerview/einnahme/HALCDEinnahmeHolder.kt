package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.einnahme

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HALCDEinnahmeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val einnahme : TextView = itemView.findViewById(R.id.item_delete_e_einnahme)
    val datum : TextView = itemView.findViewById(R.id.item_delete_e_datum)
}