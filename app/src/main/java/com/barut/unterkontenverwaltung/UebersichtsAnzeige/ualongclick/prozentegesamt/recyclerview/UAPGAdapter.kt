package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.prozentegesamt.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSRecyclerViewHolder

class UAPGAdapter(val inhalt : ArrayList<UAPGModel>)  : RecyclerView.Adapter<UAPGHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UAPGHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ua_pg_row,parent,
            false)
        return UAPGHolder(view)
    }

    override fun onBindViewHolder(holder: UAPGHolder, position: Int) {
        holder.name.setText(inhalt.get(holder.adapterPosition).name)
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.prozent.setText(inhalt.get(holder.adapterPosition).prozent)
        holder.beschreibung.setText(inhalt.get(holder.adapterPosition).beschreibung)
        clickArrowDropDown(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
    fun clickArrowDropDown(holder: UAPGHolder) {
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