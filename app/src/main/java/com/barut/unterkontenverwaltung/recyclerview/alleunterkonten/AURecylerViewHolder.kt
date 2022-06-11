package com.barut.unterkontenverwaltung.recyclerview.alleunterkonten

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class AURecylerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val unterkonto : TextView = itemView.findViewById(R.id.setItemRowName)
    val summe : TextView = itemView.findViewById(R.id.setItemRowDiffernz)
}