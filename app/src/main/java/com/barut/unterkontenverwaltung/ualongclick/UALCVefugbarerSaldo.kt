package com.barut.unterkontenverwaltung.ualongclick

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.UaStartRecyclerView
import com.barut.unterkontenverwaltung.recyclerview.ualongclick.UaModel
import com.barut.unterkontenverwaltung.sqlite.SQliteInit

class UALCVefugbarerSaldo(private val clickArea : LinearLayout,private val context : Context) {

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
    fun getData() : ArrayList<UaModel>{
        var arrayList : ArrayList<UaModel> = arrayListOf()
        for(i in sqlInit.einnahme().readData()){
            var model = UaModel(i.summe,i.datum,i.databaseType,i.id,i.beschreibung)
            arrayList.add(model)
        }
        return arrayList
    }
    fun startRecyclerView(){
        UaStartRecyclerView(createAlertDialogGetView().findViewById(R.id.ua_recyclerview_longclick),context,
        getData())
    }

}