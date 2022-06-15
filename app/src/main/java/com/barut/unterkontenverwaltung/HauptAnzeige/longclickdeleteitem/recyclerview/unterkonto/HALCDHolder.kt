package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.unterkonto

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HALCDHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val datum : TextView = itemView.findViewById(R.id.item_delete_u_datum)
    val unterkonto : TextView = itemView.findViewById(R.id.item_delete_u_unterkonto)
    val prozent : TextView = itemView.findViewById(R.id.item_delete_u_prozent)
}