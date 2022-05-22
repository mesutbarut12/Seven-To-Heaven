package com.barut.unterkontenverwaltung.recyclerview

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.MainActivity
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.showitems.ShowItems
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel

class RecyclerViewBinder(val holder : RecyclerViewHolderMain,val id : String,
                         val inhalt : ArrayList<RecylcerViewModel>,
                        val recyclerView : RecyclerView) {
    val sqLiteMainEinkommen = SQLiteMain(holder.itemView.context,"Einkommen","Einkommen",
    "unterkonto","datum","echtZeitDatum","databaseType","id")
    val sqLiteMainUnterkonto = SQLiteMain(holder.itemView.context,"Unterkonto","Unterkonto",
    "name","prozent","datum","databaseType","id")
    val sqLiteMainAusgabe = SQLiteMain(holder.itemView.context,"Ausgabe","Ausgabe",
    "unterkonto","ausgabe","datum","databaseType","id")

    fun onStart() {
        if (holder.differntHolder() != null) {
            if (id == "ShowItems") {
                val showlist = holder.differntHolder()
                val image = showlist!!.get(0) as ImageView
                val ivDelete = showlist!!.get(1) as ImageView
                val tvspaltenname1 = showlist!!.get(2) as TextView
                val tvspaltenname2 = showlist!!.get(3) as TextView
                val echtzeitDatum = showlist!!.get(4) as TextView
                if(inhalt.get(holder.adapterPosition).databaseType == "Unterkonto"){
                    image.setImageResource(R.drawable.ic_unterkonto)
                } else if(inhalt.get(holder.adapterPosition).databaseType == "Einnahme") {
                    image.setImageResource(R.drawable.ic_input)
                } else {
                    image.setImageResource(R.drawable.ic_output)
                }
                tvspaltenname1.setText(inhalt.get(holder.adapterPosition).spaltenName1Inhalt)
                tvspaltenname2.setText(inhalt.get(holder.adapterPosition).spaltenName2Inhalt)
                echtzeitDatum.setText(inhalt.get(holder.adapterPosition).date)
                onDelete(ivDelete)
            }
        } else {
            Toast.makeText(holder.itemView.context,"Fehler beim Laden",Toast.LENGTH_LONG).show()
        }
    }
    private fun onDelete(ivDelete : ImageView){
        ivDelete.setOnClickListener {
            if(inhalt.get(holder.adapterPosition).databaseType == "Unterkonto"){
                sqLiteMainUnterkonto.deleateItem(inhalt.get(holder.adapterPosition)
                    .spaltenName1Inhalt,inhalt.get(holder.adapterPosition).spaltenName2Inhalt)
            } else if(inhalt.get(holder.adapterPosition).databaseType == "Einnahme"){
                sqLiteMainEinkommen.deleateItem(inhalt.get(holder.adapterPosition)
                    .spaltenName1Inhalt,inhalt.get(holder.adapterPosition).spaltenName2Inhalt)
            } else {
                sqLiteMainAusgabe.deleateItem(inhalt.get(holder.adapterPosition)
                    .spaltenName1Inhalt,inhalt.get(holder.adapterPosition).spaltenName2Inhalt)
            }
            val updateData = updateData()
            val showItems = ShowItems(holder.itemView.context, recyclerView, updateData)
            showItems.transformSqlToRecyclerModel()
            showItems.showItems()

        }
    }
    private fun updateData() : ArrayList<SQLiteModel>{
        val arrayList : ArrayList<SQLiteModel> = arrayListOf()
        for(i in sqLiteMainEinkommen.readData()){
            arrayList.add(i)
        }
        for(i in sqLiteMainUnterkonto.readData()){
            arrayList.add(i)
        }
        for(i in sqLiteMainAusgabe.readData()){
            arrayList.add(i)
        }
        arrayList.sortBy {it.echtZeitDatum}
        return arrayList
    }
}