package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAStarterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val datum : TextView = itemView.findViewById(R.id.ha_einnahme_datum)
    val einnahme : TextView = itemView.findViewById(R.id.ha_einnahme_einnahme)
    val id : TextView = itemView.findViewById(R.id.ha_einnahme_id)
    val delete : ImageView = itemView.findViewById(R.id.ha_einnahme_delete)
}