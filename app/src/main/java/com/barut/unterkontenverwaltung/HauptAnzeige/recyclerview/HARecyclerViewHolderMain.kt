package com.barut.unterkontenverwaltung.recyclerview.hauptanzeige

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HARecyclerViewHolderMain(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val unterkonto: TextView = itemView.findViewById(R.id.tvUnterkonto)
    val prozent: TextView = itemView.findViewById(R.id.tvProzentualeEinteilung)
    val guthaben: TextView = itemView.findViewById(R.id.tvGuthaben)
    val ausgaben: TextView = itemView.findViewById(R.id.tvAusgaben)
    val ergebnis: TextView = itemView.findViewById(R.id.tvErgebnis)


}