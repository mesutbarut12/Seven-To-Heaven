package com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup

import android.content.Context
import android.view.View
import android.widget.TextView
import com.barut.unterkontenverwaltung.DataTransferEinkommenOderAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.einkommenOderausgabe.EinkommenOderAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.recyclerview.HAModel
import com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.recyclerview.HAStarter
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.HAHauptAnzeigeModel
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.HARecyclerViewHolderMain

class HAPopUp(private val context: Context, private val holder : HARecyclerViewHolderMain,
              private val inhalt : HAHauptAnzeigeModel) {

    private val sqliteinit = SQliteInit(context)
    fun init() {

        val view = createAlertAndGetView()
        setUnterkontoNameUndGesamtSumme(view)
        EinkommenOderAusgabe(view,auswahlInput(view)).init()



    }

    fun createAlertAndGetView(): View {
        val alert = AlertDialogMain(context, R.layout.ha_recyclerview)
        val view = alert.inflateLayout()
        alert.createDialog()
        return view
    }
    fun setUnterkontoNameUndGesamtSumme(view : View){
        val name : TextView = view.findViewById(R.id.ha_name1)
        val erstelltAm : TextView = view.findViewById(R.id.ha_erstelltam1)

        name.setText(inhalt.hAunterkontoName!!.get(holder.adapterPosition))
        for(i in sqliteinit.unterkonto().readData()){
            if(i.name == inhalt.hAunterkontoName!!.get(holder.adapterPosition))
            erstelltAm.setText(i.datum)

        }
    }
    fun auswahlInput(view : View) : DataTransferEinkommenOderAusgabe{
        val auswahl = object : DataTransferEinkommenOderAusgabe{
            override fun data(wahl: Int) {
                if(wahl == 0){
                    println("hallo")
                    HAStarter(
                        view.findViewById(R.id.ha_recyclerview), context,
                        getDataEinkommen()).init()
                } else if(wahl == 1){

                }
            }
        }
        return auswahl
    }
    fun getDataEinkommen() : ArrayList<HAModel>{
        var array : ArrayList<HAModel> = arrayListOf()
        var model : HAModel
        var calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()

        for(y in sqliteinit.einnahme().readData()) {
            for (i in sqliteinit.unterkonto().readData()) {
                var ergebnis = 0.0
                if (inhalt.hAunterkontoName!!.get(holder.adapterPosition) == i.name) {
                    ergebnis = (y.summe.toDouble() / 100) * i.prozent.toDouble()
                    model = HAModel(i.prozent, ergebnis.toString(),y.datum,y.summe,y.id)
                    array.add(model)
                }
            }
        }

        return array
    }
}