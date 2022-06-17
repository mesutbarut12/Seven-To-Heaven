package com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.HAPopUp
import com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.HALCDelete
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.DatePickerClass
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.EDirektModel

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
        cardViewClick(holder)
        cardViewLongClick(holder)
        clickMenu(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.hAunterkontoName!!.size
    }

    fun getValue(value: String): String {
        return value.split(".")[0]
    }
    fun cardViewClick(holder : HARecyclerViewHolderMain){
        holder.cardView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?){
                HAPopUp(holder.itemView.context,holder,inhalt).init()

            }
        })
    }
    fun cardViewLongClick(holder : HARecyclerViewHolderMain){
        holder.cardView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                println("long Click")
                HALCDelete(holder.itemView.context,holder).init()
                return false
            }
        })
    }
    fun clickMenu(holder: HARecyclerViewHolderMain){
        holder.menuImage.setOnClickListener {
            val sQliteInit = SQliteInit(holder.itemView.context)
            sQliteInit.eDirekt()
            val alert = AlertDialogMain(holder.itemView.context, R.layout.set_item_einkommen)
            val view = alert.inflateLayout()
            val etEinkommen = view.findViewById<EditText>(R.id.setItemEinkommenEinkommenEt)
            val etDatum = view.findViewById<EditText>(R.id.setItemEinkommenDatumEt)
            val etBeschreibung = view.findViewById<EditText>(R.id.setItemEinkommenBeschreibungEt)
            val etSpeichern = view.findViewById<Button>(R.id.setItemEinkommenSpeichern)
            val datepicker = DatePickerClass(holder.itemView.context,etDatum)

            etDatum.setText(datepicker.getTodaysDate())
            etDatum.setOnClickListener {
                datepicker.initDatePicker()
            }


            etSpeichern.setOnClickListener {
                if(etEinkommen.text.toString().isNotEmpty() && etDatum.text.toString().isNotEmpty()){
                    alert.cancelDialog()
                    val model = EDirektModel(etEinkommen.text.toString(),
                    etDatum.text.toString(),"eDirekt","",etBeschreibung.text.toString())
                    sQliteInit.eDirekt().setData(model)
                }
            }
            alert.createDialog()
            Toast.makeText(holder.itemView.context,"Demnächst verfügbar",Toast.LENGTH_SHORT).show()
        }
    }
}


