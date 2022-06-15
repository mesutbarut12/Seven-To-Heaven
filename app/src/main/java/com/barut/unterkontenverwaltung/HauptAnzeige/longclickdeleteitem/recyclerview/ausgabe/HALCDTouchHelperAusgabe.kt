package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.unterkonto

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.ausgabe.HALCDModelAusgabe
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel

class HALCDTouchHelperAusgabe(
    val inhalt: ArrayList<HALCDModelAusgabe>,
    private val context: Context,
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val sqlinit = SQliteInit(context)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        var model: AusgabenModel
        for (i in sqlinit.ausgabe().readData()) {
            if (inhalt.get(pos).name == i.unterkonto
                && inhalt.get(pos).id == i.id && inhalt.get(pos).summe == i.summe
                && inhalt.get(pos).datum == i.datum
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
    }

}