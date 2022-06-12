package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.verbindenundstarten

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.recyclerview.UAGAModel
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UAGAStarter
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class UALCGesamtAusgaben(private val clickArea : LinearLayout, private val context : Context) {

    private var sqlInit = SQliteInit(context)

    fun init() {

        clickArea.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                if (getData().isNotEmpty()) {
                    startRecyclerView()
                }
                return false
            }
        })

    }
    fun getData() : ArrayList<UAGAModel>{
        var arrayList : ArrayList<UAGAModel> = arrayListOf()
        var model : UAGAModel
        for(i in sqlInit.ausgabe().readData()){
            model = UAGAModel(i.unterkonto,i.datum,i.summe.toDouble().toString(),i.beschreibung)
            arrayList.add(model)
        }
        return arrayList
    }

    fun createAlertDialogGetView() : View{
        var alert = AlertDialogMain(context, R.layout.ua_recyclerview)
        var view = alert.inflateLayout()
        alert.createDialog()
        return view
    }
    fun startRecyclerView(){
        UAGAStarter(createAlertDialogGetView().findViewById(R.id.ua_recyclerview_longclick),context,
            getData())
    }
}