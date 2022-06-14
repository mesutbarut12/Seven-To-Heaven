package com.barut.unterkontenverwaltung.mainactivity.bottomnavigation

import android.content.Context
import android.view.MenuItem
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.mainactivity.bottomnavigation.additem.Starter
import com.barut.unterkontenverwaltung.save.GetDataForSave
import com.barut.unterkontenverwaltung.save.SaveAndPut
import com.barut.unterkontenverwaltung.save.SaveModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

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

    fun init(
        bottomNavigationView: BottomNavigationView,
        context: Context, dataTransferUserAddedItem: DataTransferUserAddedItem
    ) {
        bottomNavigationView.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.addItem) {
                    var starter = Starter(context, dataTransferUserAddedItem)
                    starter.init()

                }
                else if (item.itemId == R.id.datenSpeichern) {
                    val getDataForSave = GetDataForSave(context)
                    val model = SaveModel(
                        getDataForSave.getUnterkonto(),
                        getDataForSave.getEinnahme(), getDataForSave.getAusgaben()
                    )

                    saveAndPut.save(model,  null)
                }
                else if(item.itemId == R.id.datenZiehen){
                    saveAndPut.getData(null)
                }
                return false
            }
        })
    }
}