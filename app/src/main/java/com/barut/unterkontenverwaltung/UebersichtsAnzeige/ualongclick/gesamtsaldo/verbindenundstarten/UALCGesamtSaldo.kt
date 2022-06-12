package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.verbindenundstarten

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSStartRecyclerView
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSModel
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class UALCGesamtSaldo(private val clickArea : LinearLayout, private val context : Context) {

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
    fun getData() : ArrayList<UaGSModel>{
        var arrayList : ArrayList<UaGSModel> = arrayListOf()
        for(i in sqlInit.einnahme().readData()){
            var model = UaGSModel(i.summe,i.datum,i.databaseType,i.id,i.beschreibung)
            arrayList.add(model)
        }
        return arrayList
    }
    fun startRecyclerView(){
        UaGSStartRecyclerView(createAlertDialogGetView().findViewById(R.id.ua_recyclerview_longclick),context,
        getData())
    }

}