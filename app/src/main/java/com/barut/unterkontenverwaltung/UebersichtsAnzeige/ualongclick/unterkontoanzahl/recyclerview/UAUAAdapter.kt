package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.unterkontoanzahl.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSRecyclerViewHolder

class UAUAAdapter(val inhalt : ArrayList<UAUAModel>) : RecyclerView.Adapter<UAUAHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UAUAHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ua_ua_row,parent,
        false)
        return UAUAHolder(view)

    }

    override fun onBindViewHolder(holder: UAUAHolder, position: Int) {
        holder.name.setText(inhalt.get(holder.adapterPosition).name)
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.beschreibung.setText(inhalt.get(holder.adapterPosition).beschreibung)
        holder.prozent.setText(inhalt.get(holder.adapterPosition).prozent)
        clickArrowDropDown(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
    fun clickArrowDropDown(holder: UAUAHolder) {
        holder.itemView.setOnClickListener {
            var boolean = holder.beschreibung.visibility == View.GONE
            if (boolean == true) {
                holder.beschreibung.visibility = View.VISIBLE
            } else if (boolean == false) {
                holder.beschreibung.visibility = View.GONE
            }
        }
    }
}