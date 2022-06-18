package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel

class HAAusgabeAdapter(val inhalt: ArrayList<HAAusgabeModel>) :
    RecyclerView.Adapter<HAAusgabeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HAAusgabeHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.ha_ausgabe_row, parent, false)
        return HAAusgabeHolder(view)
    }

    override fun onBindViewHolder(holder: HAAusgabeHolder, position: Int) {
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.guthaben.setText(inhalt.get(holder.adapterPosition).guthaben + "€")
        holder.ausgabe.setText(inhalt.get(holder.adapterPosition).ausgabe + "€")
        holder.ergebnis.setText(inhalt.get(holder.adapterPosition).ergebnis + "€")
        holder.id.setText(inhalt.get(holder.adapterPosition).id)
        delete(holder)
    }

    fun delete(holder: HAAusgabeHolder) {
        val sqlinit = SQliteInit(holder.itemView.context)
        val pos = holder.adapterPosition
        var model: AusgabenModel
        holder.delete.setOnClickListener {
            for (i in sqlinit.ausgabe().readData()) {

                if (inhalt.get(pos).id == i.id
                    && inhalt.get(pos).datum == i.datum &&
                    inhalt.get(pos).ausgabe == i.summe
                ) {
                    model = AusgabenModel(
                        i.unterkonto,
                        i.summe,
                        i.datum,
                        i.databaseType,
                        i.id,
                        i.beschreibung
                    )
                    sqlinit.ausgabe().deleateItem(model)
                }
            }
            inhalt.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}