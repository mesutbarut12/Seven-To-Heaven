package com.barut.unterkontenverwaltung.save

import android.content.Context
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel

class GetDataForSave(val context: Context) {

    val sqlinit = SQliteInit(context)

    fun getUnterkonto() : ArrayList<UnterkontoModel>{
        var model : UnterkontoModel
        var arrayList : ArrayList<UnterkontoModel> = arrayListOf()
        for(i in sqlinit.unterkonto().readData()){
            model = UnterkontoModel(i.name,i.prozent,i.datum,i.databaseType,i.id,i.beschreibung)
            arrayList.add(model)
        }
        return arrayList
    }
    fun getEinnahme() : ArrayList<EinkommenModel>{
        var model : EinkommenModel
        var arrayList : ArrayList<EinkommenModel> = arrayListOf()
        for(i in sqlinit.einnahme().readData()){
            model = EinkommenModel(i.summe,i.datum,i.databaseType,i.id,i.beschreibung)
            arrayList.add(model)
        }
        return arrayList
    }
    fun getAusgaben() : ArrayList<AusgabenModel>{
        var model : AusgabenModel
        var arrayList : ArrayList<AusgabenModel> = arrayListOf()
        for(i in sqlinit.ausgabe().readData()){
            model = AusgabenModel(i.unterkonto,i.summe,i.datum,i.databaseType,i.id,i.beschreibung)
            arrayList.add(model)
        }
        return arrayList
    }
}