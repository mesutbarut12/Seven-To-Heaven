package com.barut.unterkontenverwaltung.recyclerview.binder

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class ShowAllDatasInRecyclerViewBinder(val holder : RecyclerViewHolderMain, val id : String,
                                       val inhalt : ArrayList<Model>,
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
                tvspaltenname1.setText(inhalt.get(holder.adapterPosition).spaltenName1)
                tvspaltenname2.setText(inhalt.get(holder.adapterPosition).spaltenName2)
                echtzeitDatum.setText(inhalt.get(holder.adapterPosition).datum)
                onDelete(ivDelete)
            }
        } else {
            Toast.makeText(holder.itemView.context,"Fehler beim Laden",Toast.LENGTH_LONG).show()
        }
    }
    private fun onDelete(ivDelete : ImageView){
        ivDelete.setOnClickListener {
            if(inhalt.get(holder.adapterPosition).databaseType == "Unterkonto"){
                var adapter = holder.adapterPosition

                if(sqLiteMainAusgabe.readData().isNotEmpty()){
                    for(i in sqLiteMainAusgabe.readData()){
                        println(i.spaltenName2 + " " + inhalt.get(adapter).spaltenName1)
                        if(i.spaltenName2 == inhalt.get(adapter).spaltenName1){
                            alertDialog(object : AlertDialogUser{
                                override fun getClick(klick: Int) {
                                    if(klick == 1){
                                        sqLiteMainAusgabe.deleteArgs(inhalt.get(adapter).spaltenName1)
                                        sqLiteMainUnterkonto.deleateItem(inhalt.get(adapter)
                                            .spaltenName1,inhalt.get(adapter).spaltenName2)
                                    } else if (klick == 2) {
                                        sqLiteMainUnterkonto.deleateItem(inhalt.get(adapter)
                                            .spaltenName1,inhalt.get(adapter).spaltenName2)
                                    }
                                    val updateData = updateData()
                                    StartRecyclerView(holder.itemView.context,recyclerView,
                                        updateData,R.layout.show_items,"ShowItems",null,null)

                                }

                            })
                        } else {
                            sqLiteMainUnterkonto.deleateItem(inhalt.get(adapter)
                                .spaltenName1,inhalt.get(adapter).spaltenName2)
                        }
                    }
                } else {
                    sqLiteMainUnterkonto.deleateItem(inhalt.get(adapter)
                        .spaltenName1,inhalt.get(adapter).spaltenName2)
                }




            } else if(inhalt.get(holder.adapterPosition).databaseType == "Einnahme"){
                sqLiteMainEinkommen.deleateItem(inhalt.get(holder.adapterPosition)
                    .spaltenName1,inhalt.get(holder.adapterPosition).spaltenName2)
            } else {
                sqLiteMainAusgabe.deleateItem(inhalt.get(holder.adapterPosition)
                    .spaltenName1,inhalt.get(holder.adapterPosition).spaltenName2)
            }
            val updateData = updateData()
            StartRecyclerView(holder.itemView.context,recyclerView,
                updateData,R.layout.show_items,"ShowItems",null,null)


        }
    }
    private fun updateData() : ArrayList<Model>{
        val arrayList : ArrayList<Model> = arrayListOf()
        for(i in sqLiteMainEinkommen.readData()){
            arrayList.add(i)
        }
        for(i in sqLiteMainUnterkonto.readData()){
            arrayList.add(i)
        }
        for(i in sqLiteMainAusgabe.readData()){
            arrayList.add(i)
        }
        arrayList.sortBy {it.datum}
        return arrayList
    }
    private fun alertDialog(getClick : AlertDialogUser){
        val alert =  AlertDialog.Builder(holder.itemView.context)
        alert.setTitle("!!!1ACHTUNG!!!")
        alert.setMessage("Du bist grad dabei ein Unterkonto zu löschen, sollen die damit verbundenen ausgaben" +
                " mit gelöscht werden?")
        alert.setPositiveButton("JA",object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                getClick.getClick(1)
                println("Ja geklickt!")
            }

        }).setNegativeButton("Nein", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                getClick.getClick(2)
            }

        }).create().show()
    }
}
interface AlertDialogUser{
    fun getClick(klick : Int)
}