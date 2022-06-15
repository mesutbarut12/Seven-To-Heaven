package com.barut.unterkontenverwaltung.mainactivity.bottomnavigation

import android.content.Context
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.save.GetDataForSave
import com.barut.unterkontenverwaltung.save.SaveAndPut
import com.barut.unterkontenverwaltung.save.SaveModel


class BottomNavigation(val context: Context) {


    //Die Klasse Starter startet den  Pop Up Alert Dialog
    // und anschließend connected der mit der funktion das
    // der user mit den buttons inteagieren kann
    val sqlinit = SQliteInit(context)
    val saveAndPut = SaveAndPut(
        context,
        sqlinit.einnahme(),
        sqlinit.ausgabe(),
        sqlinit.unterkonto()
    )




    fun datenSpeichern() {
        val getDataForSave = GetDataForSave(context)
        val model = SaveModel(
            getDataForSave.getUnterkonto(),
            getDataForSave.getEinnahme(), getDataForSave.getAusgaben()
        )

        saveAndPut.save(model, null)
    }
    fun datenZiehen(){
        saveAndPut.getData(null)
    }
}
