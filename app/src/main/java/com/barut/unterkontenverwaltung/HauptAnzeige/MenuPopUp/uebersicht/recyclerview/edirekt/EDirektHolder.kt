package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.edirekt

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class EDirektHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val datum : TextView = itemView.findViewById(R.id.ha_edirekt_datum)
    val summe : TextView = itemView.findViewById(R.id.ha_edirekt_summe)
    val unterkonto : TextView = itemView.findViewById(R.id.ha_edirekt_unterkonto)
    val delete : ImageView = itemView.findViewById(R.id.ha_edirekt_delete)


}