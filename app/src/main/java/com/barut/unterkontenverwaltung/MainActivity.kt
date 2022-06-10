package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.barut.unterkontenverwaltung.bottomnavigation.BottomNavigation
import com.barut.unterkontenverwaltung.calculate.hauptanzeige.CalculateHauptAnzeige
import com.barut.unterkontenverwaltung.calculate.starter.CalculateStarter
import com.barut.unterkontenverwaltung.recyclerview.UserIdModel
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.HAStartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQliteInit


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        BottomNavigation().init(findViewById(R.id.bottomNavigation), this)

        calculateUA()
        calculateHA()
    }

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

    fun calculateHA() {
        var calculateHauptAnzeige = CalculateHauptAnzeige(this)
        calculateHauptAnzeige.init()
        calculateHauptAnzeige.setDataInDataBase()
        /*HAStartRecyclerView(
                findViewById(R.id.recyclerView),
                this,calculateHauptAnzeige.init()!!
            )
             */

    }



}