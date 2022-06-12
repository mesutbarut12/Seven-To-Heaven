package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.verbindenundstarten

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSStartRecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaVSStarter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.recyclerview.UaVSModel
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class UaVSLongClick(private val clickArea: LinearLayout, private val context: Context) {

    private var sqlInit = SQliteInit(context)

    fun init() {
        clickArea.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                if (getData().isNotEmpty()) {
                    startRecyclerView()
                } else {
                    Toast.makeText(context, "Du verfügst über keine Ausgabe", Toast.LENGTH_SHORT)
                        .show()
                }
                return false
            }
        })
    }

    fun createAlertDialogGetView(): View {
        var alert = AlertDialogMain(context, R.layout.ua_recyclerview)
        var view = alert.inflateLayout()
        alert.createDialog()
        return view
    }

    fun getData(): ArrayList<UaVSModel> {
        val calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()
        var gesamtSaldo = calculateUebersichtsAnzeige.uAGesamtSaldo()
        var ergebnis = gesamtSaldo.toDouble()
        var model: UaVSModel
        var arrayList: ArrayList<UaVSModel> = arrayListOf()
        for (i in sqlInit.ausgabe().readData()) {
            ergebnis -= i.summe.toDouble()
            model = UaVSModel(
                (ergebnis + i.summe.toDouble()).toString(),
                i.summe.toDouble().toString(),
                i.unterkonto,
                i.datum,
                i.beschreibung,
                ergebnis.toString()
            )
            arrayList.add(model)
        }
        return arrayList
    }

    fun startRecyclerView() {
        UaVSStarter(
            createAlertDialogGetView().findViewById(R.id.ua_recyclerview_longclick), context,
            getData()
        )
    }
}