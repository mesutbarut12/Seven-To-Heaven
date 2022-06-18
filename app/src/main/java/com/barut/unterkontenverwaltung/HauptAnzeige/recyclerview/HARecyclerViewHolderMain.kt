package com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HARecyclerViewHolderMain(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val unterkonto: TextView = itemView.findViewById(R.id.tvUnterkonto)
    val prozent: TextView = itemView.findViewById(R.id.tvProzentualeEinteilung)
    val guthaben: TextView = itemView.findViewById(R.id.tvGuthaben)
    val ausgaben: TextView = itemView.findViewById(R.id.tvAusgaben)
    val ergebnis: TextView = itemView.findViewById(R.id.tvErgebnis)
    val cardView: CardView = itemView.findViewById(R.id.cardViewUebersicht)
    val menuImage: ImageView = itemView.findViewById(R.id.ha_row_menu)





}