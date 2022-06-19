package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAAusgabeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val datum : TextView = itemView.findViewById(R.id.ha_ausgabe_datum)
    val ausgabe : TextView = itemView.findViewById(R.id.ha_ausgabe_ausgabe)
    val unterkonto : TextView = itemView.findViewById(R.id.ha_ausgabe_unterkonto)
    val delete : ImageView = itemView.findViewById(R.id.ha_ausgabe_delete)
}