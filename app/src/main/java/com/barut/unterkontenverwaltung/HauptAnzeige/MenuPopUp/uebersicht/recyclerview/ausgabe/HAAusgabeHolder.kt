package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAAusgabeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val datum : TextView = itemView.findViewById(R.id.ha_ausgabe_datum)
    val guthaben : TextView = itemView.findViewById(R.id.ha_ausgabe_guthaben)
    val ausgabe : TextView = itemView.findViewById(R.id.ha_ausgabe_ausgabe)
    val ergebnis : TextView = itemView.findViewById(R.id.ha_ausgabe_ergebnis)
    val id : TextView = itemView.findViewById(R.id.ha_ausgabe_id)
    val delete : ImageView = itemView.findViewById(R.id.ha_ausgabe_delete)
}