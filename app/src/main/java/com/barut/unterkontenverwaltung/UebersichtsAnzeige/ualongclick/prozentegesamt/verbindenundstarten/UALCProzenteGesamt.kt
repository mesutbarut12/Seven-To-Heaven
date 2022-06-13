package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.verbindenundstarten

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UAPGStarter
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSStartRecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSModel
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.prozentegesamt.recyclerview.UAPGAdapter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.prozentegesamt.recyclerview.UAPGModel
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class UALCProzenteGesamt(private val clickArea: LinearLayout, private val context: Context) {

    private var sqlInit = SQliteInit(context)

    fun init() {

        clickArea.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                if (getData().isNotEmpty()) {
                    startRecyclerView()
                } else {
                    Toast.makeText(context, "Du verfügst über kein Unterkonto!", Toast.LENGTH_SHORT)
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

    fun getData(): ArrayList<UAPGModel> {
        var arrayList: ArrayList<UAPGModel> = arrayListOf()
        for (i in sqlInit.unterkonto().readData()) {
            var model = UAPGModel(
                i.name,i.datum,i.prozent,i.beschreibung
            )
            arrayList.add(model)
        }
        return arrayList
    }

    fun startRecyclerView() {
        UAPGStarter(
            createAlertDialogGetView().findViewById(R.id.ua_recyclerview_longclick), context,
            getData()
        )
    }

}