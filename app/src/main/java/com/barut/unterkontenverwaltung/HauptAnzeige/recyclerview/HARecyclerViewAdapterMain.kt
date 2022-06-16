package com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.HAPopUp
import com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.HALCDelete

class HARecyclerViewAdapterMain(
    val inhalt: HAHauptAnzeigeModel, val layout: Int,

    ) : RecyclerView.Adapter<HARecyclerViewHolderMain>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HARecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return HARecyclerViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: HARecyclerViewHolderMain, position: Int) {
        println(getValue(inhalt.hAGuthaben!!.get(holder.adapterPosition)))
        holder.unterkonto.setText(inhalt.hAunterkontoName!!.get(holder.adapterPosition))
        holder.prozent.setText("Prozent : ${getValue(inhalt.hAProzentEinteilung!!.get(holder.adapterPosition))}%")
        holder.ausgaben.setText("Ausgabe : ${getValue(inhalt.hAAusgaben!!.get(holder.adapterPosition))}€")
        holder.guthaben.setText("Guthaben : ${getValue(inhalt.hAGuthaben!!.get(holder.adapterPosition))}€")
        holder.ergebnis.setText("Ergebnis : ${getValue(inhalt.hAErgebnis!!.get(holder.adapterPosition))}€")
        onClick(holder)
        longClick(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.hAunterkontoName!!.size
    }

    fun getValue(value: String): String {
        return value.split(".")[0]
    }
    fun onClick(holder : HARecyclerViewHolderMain){
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?){
                HAPopUp(holder.itemView.context,holder,inhalt).init()

            }
        })
    }
    fun longClick(holder : HARecyclerViewHolderMain){
        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                println("long Click")
                HALCDelete(holder.itemView.context,holder).init()
                return false
            }
        })
    }
}

