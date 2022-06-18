package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.barut.unterkontenverwaltung.DataTransferEinkommenOderAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.einkommenOderausgabe.EinkommenOderAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe.HAAusgabeModel
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.popup.recyclerview.einnahme.HAAusgabeStarter
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme.HAEinnahmeModel
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme.HAEinnahmeStarter
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview.HAHauptAnzeigeModel
import com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview.HARecyclerViewHolderMain

class HAPopUp(
    private val context: Context, private val holder: HARecyclerViewHolderMain,
    private val inhalt: HAHauptAnzeigeModel
) {



    private val sqliteinit = SQliteInit(context)
    fun init() {

        val view = createAlertAndGetView()
        var einkommenOderAusgabe = EinkommenOderAusgabe(view, auswahlInput(view))
        einkommenOderAusgabe.init()
        setUnterkontoNameUndGesamtSumme(view)



    }

    fun createAlertAndGetView(): View {
        val alert = AlertDialogMain(context, R.layout.ha_recyclerview)
        val view = alert.inflateLayout()
        alert.createDialog()
        return view
    }

    fun setUnterkontoNameUndGesamtSumme(view: View) {
        val name: TextView = view.findViewById(R.id.ha_name1)
        val erstelltAm: TextView = view.findViewById(R.id.ha_erstelltam1)

        name.setText(inhalt.hAunterkontoName!!.get(holder.adapterPosition))
        for (i in sqliteinit.unterkonto().readData()) {
            if (i.name == inhalt.hAunterkontoName!!.get(holder.adapterPosition))
                erstelltAm.setText(i.datum)

        }
    }

    fun auswahlInput(view: View): DataTransferEinkommenOderAusgabe {
        val auswahl = object : DataTransferEinkommenOderAusgabe {
            override fun data(wahl: Int) {
                if (wahl == 0) {
                    HAEinnahmeStarter(
                        view.findViewById(R.id.ha_recyclerview), context,
                        getDataEinkommen()
                    ).init()
                } else if (wahl == 1) {
                    HAAusgabeStarter(
                        view.findViewById(R.id.ha_recyclerview), context,
                        getDataAusgaben()
                    ).init()
                } else if(wahl == 2){

                }
            }
        }
        return auswahl
    }

    fun getDataEinkommen(): ArrayList<HAEinnahmeModel> {
        var array: ArrayList<HAEinnahmeModel> = arrayListOf()
        var model: HAEinnahmeModel
        var calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()

        if (sqliteinit.einnahme().readData().isNotEmpty()) {
            for (y in sqliteinit.einnahme().readData()) {
                        model = HAEinnahmeModel(y.datum,y.summe,y.id,y.beschreibung)
                        array.add(model)
                    }

            } else {
            Toast.makeText(context, "Nicht genügend Daten vorhanden!", Toast.LENGTH_SHORT).show()

        }


        return array
    }

    fun getDataAusgaben(): ArrayList<HAAusgabeModel> {
        var arraylist: ArrayList<HAAusgabeModel> = arrayListOf()
        var calculateHauptAnzeige = CalculateUebersichtsAnzeige(context)
        calculateHauptAnzeige.init()
        var gSaldo = calculateHauptAnzeige.uAGesamtSaldo()
        var model: HAAusgabeModel
        var guthaben = 0.0
        if (sqliteinit.unterkonto().readData().isNotEmpty() && sqliteinit.ausgabe().readData()
                .isNotEmpty()
        ) {
            for (i in sqliteinit.unterkonto().readData()) {
                if (i.name == inhalt.hAunterkontoName!!.get(holder.adapterPosition)) {
                    guthaben = (gSaldo.toDouble() / 100.0) * i.prozent.toDouble()
                    for (y in sqliteinit.ausgabe().readData()) {
                        if (y.unterkonto == inhalt.hAunterkontoName!!.get(holder.adapterPosition)) {
                            guthaben -= y.summe.toDouble()
                            model = HAAusgabeModel(
                                y.datum, (guthaben + y.summe.toDouble()).toString(),
                                y.summe, guthaben.toString(), y.id
                            )
                            arraylist.add(model)
                        }
                    }
                }
            }
        } else {
            Toast.makeText(context, "Keine Ausgaben!", Toast.LENGTH_SHORT).show()
        }

        return arraylist
    }
}