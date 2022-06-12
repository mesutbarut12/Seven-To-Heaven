package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.verbindenundstarten

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSStartRecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaVSStarter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.recyclerview.UaVSModel
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class UaVSLongClick(private val clickArea : LinearLayout, private val context : Context) {

    private var sqlInit = SQliteInit(context)

    fun init(){
        clickArea.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                startRecyclerView()
                return false
            }
        })
    }
    fun createAlertDialogGetView() : View{
        var alert = AlertDialogMain(context, R.layout.ua_recyclerview)
        var view = alert.inflateLayout()
        alert.createDialog()
        return view
    }
    fun getData() : ArrayList<UaVSModel>{
        val calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()
        var gesamtSaldo = calculateUebersichtsAnzeige.uAGesamtSaldo()
        var ergebnis = gesamtSaldo.toDouble()
        var model : UaVSModel
        var arrayList : ArrayList<UaVSModel> = arrayListOf()
        for(i in sqlInit.ausgabe().readData()){
            model = UaVSModel(ergebnis.toString(),i.summe,i.unterkonto,i.datum,i.beschreibung)
            ergebnis -= i.summe.toDouble()
            arrayList.add(model)
        }
        return arrayList
    }

    fun startRecyclerView(){
        UaVSStarter(createAlertDialogGetView().findViewById(R.id.ua_recyclerview_longclick),context,
            getData())
    }
}