package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete

import android.content.Context
import android.view.View
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.ausgabe.HALCDModelAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.einnahme.HALCDEinnahmeModel
import com.barut.unterkontenverwaltung.HauptAnzeige.delete.recyclerview.unterkonto.HALCDEinnahmeStarter
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.unterkonto.HALCDModelUnterkonto
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.unterkonto.HALCDUnterkontoStarter
import com.barut.unterkontenverwaltung.HauptAnzeige.delete.recyclerview.unterkonto.HALCDStarterAusgabe
import com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview.HARecyclerViewHolderMain
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.google.android.material.tabs.TabLayout

class HALCDelete(
    private val context: Context,
    private val holder: HARecyclerViewHolderMain,

    ) {
    private val sQliteInit = SQliteInit(context)
    fun init() {
        val view = createDialogGetView()
        initTablayout(view)
    }

    private fun createDialogGetView(): View {
        val alert = AlertDialogMain(context, R.layout.item_delete)
        val view = alert.inflateLayout()
        alert.createDialog()
        return view
    }

    fun getUnterkonten(): ArrayList<HALCDModelUnterkonto> {
        var arrayList: ArrayList<HALCDModelUnterkonto> = arrayListOf()
        var model: HALCDModelUnterkonto
        for (i in sQliteInit.unterkonto().readData()) {
            model = HALCDModelUnterkonto(i.datum, i.name, i.prozent, i.beschreibung, i.id)
            arrayList.add(model)
        }
        return arrayList
    }

    fun getEinnahmen(): ArrayList<HALCDEinnahmeModel> {
        var arrayList: ArrayList<HALCDEinnahmeModel> = arrayListOf()
        var model: HALCDEinnahmeModel
        for (i in sQliteInit.einnahme().readData()) {
            model = HALCDEinnahmeModel(i.datum, i.summe, i.beschreibung, i.id)
            arrayList.add(model)
        }
        return arrayList
    }

    fun getAusgaben(): ArrayList<HALCDModelAusgabe> {
        var arrayList: ArrayList<HALCDModelAusgabe> = arrayListOf()
        var model: HALCDModelAusgabe
        for (i in sQliteInit.ausgabe().readData()) {
            model = HALCDModelAusgabe(i.datum, i.summe, i.unterkonto, i.id)
            arrayList.add(model)
        }
        return arrayList
    }


    fun initTablayout(view: View) {
        val tabLayout = view.findViewById<TabLayout>(R.id.item_delete_tablayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                itemSelected(tab!!.position,view)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                itemSelected(tab!!.position,view)
            }
        })

    }

    fun itemSelected(pos: Int,view : View) {
        if(pos == 0){
            HALCDEinnahmeStarter(view.findViewById(R.id.item_delete_recyclerview), context, getEinnahmen())

        } else if(pos == 1){
            HALCDStarterAusgabe(view.findViewById(R.id.item_delete_recyclerview), context,getAusgaben())

        }else if(pos == 2){
            HALCDUnterkontoStarter(view.findViewById(R.id.item_delete_recyclerview), context, getUnterkonten())

        }
    }

}