package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.barut.unterkontenverwaltung.bottomnavigation.BottomNavigation
import com.barut.unterkontenverwaltung.calculate.hauptanzeige.CalculateHauptAnzeige
import com.barut.unterkontenverwaltung.calculate.starter.CalculateStarter
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.HAStartRecyclerView


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation()

        calculateUA()
        calculateHA()
    }

    //Anzeige Oben
    //Übersichts Anzeige
    fun calculateUA() {
        var arrayList = arrayListOf<View>(
            findViewById(R.id.amVerfugbarerSaldo),
            findViewById(R.id.amGesamtSaldo),
            findViewById(R.id.amGesamtAusgaben),
            findViewById(R.id.amunterkontoAnzahl),
            findViewById(R.id.amprozentAnzahl),
            findViewById(R.id.ambeschreibungVorhanden)
        )
        CalculateStarter(this, arrayList).initUebersichtsAnzeige()
    }
    //Haupt Anzeige Mitte
    fun calculateHA() {
        var calculateHauptAnzeige = CalculateHauptAnzeige(this)
        calculateHauptAnzeige.init()
        var data = calculateHauptAnzeige.setDataInDataBase()
        HAStartRecyclerView(findViewById(R.id.recyclerView),this,data!!)
    }



    //Bottom Navigation
    fun update(){
        calculateUA()
    }
    fun bottomNavigation(){
        BottomNavigation().init(findViewById(R.id.bottomNavigation), this,bottomNavigationInterface())
    }
    fun bottomNavigationInterface() : DataTransferUserAddedItem{
        var data = object : DataTransferUserAddedItem{
            override fun data(addedItem: Boolean) {
                if(addedItem == true){
                    update()
                }
            }
        }
        return data
    }
    //Bis hier hin Bottom Navigation

}