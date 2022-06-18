package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.unterkonto

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAUnterkontoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val datum : TextView = itemView.findViewById(R.id.ha_unterkonto_datum)
    val unterkonto : TextView = itemView.findViewById(R.id.ha_unterkonto_unterkonto)
    val prozent : TextView = itemView.findViewById(R.id.ha_unterkonto_prozent)
    val delete : ImageView = itemView.findViewById(R.id.ha_unterkonto_delete)

}