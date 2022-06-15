package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.unterkonto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HALCDAdapterUnterkonto(val inhalt : ArrayList<HALCDModelUnterkonto>) : RecyclerView.Adapter<HALCDUnterkontoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HALCDUnterkontoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_delete_unterkonto_row,
        parent,false)
        return HALCDUnterkontoHolder(view)
    }

    override fun onBindViewHolder(holder: HALCDUnterkontoHolder, position: Int) {
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.unterkonto.setText(inhalt.get(holder.adapterPosition).unterkonto)
        holder.prozent.setText(inhalt.get(holder.adapterPosition).prozent)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

}