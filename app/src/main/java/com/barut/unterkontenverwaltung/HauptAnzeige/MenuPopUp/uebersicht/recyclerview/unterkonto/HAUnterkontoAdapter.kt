package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.unterkonto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferPopUpDelete
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme.HAStarterHolder
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel

class HAUnterkontoAdapter(
    val inhalt: ArrayList<HAUnterkontoModel>,
    val dataTransferPopUpDelete: DataTransferPopUpDelete
) :
    RecyclerView.Adapter<HAUnterkontoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HAUnterkontoHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.ha_unterkonto_row,
            parent, false
        )
        return HAUnterkontoHolder(view)
    }

    override fun onBindViewHolder(holder: HAUnterkontoHolder, position: Int) {
        holder.datum.setText(inhalt.get(position).datum)
        holder.unterkonto.setText(inhalt.get(position).unterkonto)
        holder.prozent.setText(inhalt.get(position).prozent)
        delete(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    fun delete(holder: HAUnterkontoHolder) {
        val sqlinit = SQliteInit(holder.itemView.context)
        val pos = holder.adapterPosition

        holder.delete.setOnClickListener {
            var model: UnterkontoModel
            for (i in sqlinit.unterkonto().readData()) {

                if (inhalt.get(pos).datum == i.datum &&
                    inhalt.get(pos).unterkonto == i.name
                    && inhalt.get(pos).prozent == i.prozent
                ) {
                    model = UnterkontoModel(
                        i.name, i.prozent, i.datum, i.databaseType, i.id, i.beschreibung
                    )
                    sqlinit.unterkonto().deleateItem(model)
                    dataTransferPopUpDelete.data(2)
                }
            }

        }

    }
}