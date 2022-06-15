package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateStarter
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.StartUALongClick
import com.barut.unterkontenverwaltung.mainactivity.bottomnavigation.BottomNavigation
import com.barut.unterkontenverwaltung.HauptAnzeige.calculate.CalculateHauptAnzeige
import com.barut.unterkontenverwaltung.mainactivity.userId.UserID
import com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview.HAStartRecyclerView
import com.barut.unterkontenverwaltung.mainactivity.FloatingAction


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUserId()
        startUA()
        calculateHA()
        swipeRefreshLayout()
        floatingActionBar()
    }

    fun floatingActionBar(){
        FloatingAction(this,findViewById(R.id.fAbAdd),object : DataTransferUserAddedItem{
            override fun data(addedItem: Boolean) {
                update()
            }
        }).init()

    }

    private fun setUserId() {
        var userid = UserID(this)
        userid.init()
        userid.getUserId()
    }

    //Anzeige Oben
    //Übersichts Anzeige
    fun startUA() {
        calculateUA()
        longClick()
    }

    fun longClick() {
        StartUALongClick(
            this, findViewById(R.id.uALCGesamtSaldo), "" +
                    "UAGesamdSaldo"
        ).init()
        StartUALongClick(
            this, findViewById(R.id.uALCVerfugbarerSaldo),
            "UAVerfügbarerSaldo"
        ).init()
        StartUALongClick(
            this, findViewById(R.id.uALCGesamtAusgaben),
            "UAGesamtAusgaben"
        ).init()
        StartUALongClick(
            this, findViewById(R.id.uALCUnterkontoAnzahl),
            "UAUnterkontoAnzahl"
        ).init()
        StartUALongClick(
            this, findViewById(R.id.uALCProzenteGesamt),
            "UAProzenteGesamt"
        ).init()
    }

    fun calculateUA() {
        var arrayList = arrayListOf<View>(
            findViewById(R.id.amVerfugbarerSaldo),
            findViewById(R.id.amGesamtSaldo),
            findViewById(R.id.amGesamtAusgaben),
            findViewById(R.id.amunterkontoAnzahl),
            findViewById(R.id.amprozentAnzahl),

            )
        CalculateStarter(this, arrayList).initUebersichtsAnzeige()
    }

    //Haupt Anzeige Mitte
    fun calculateHA() {
        var calculateHauptAnzeige = CalculateHauptAnzeige(this)
        calculateHauptAnzeige.init()
        var data = calculateHauptAnzeige.setData()
        HAStartRecyclerView(findViewById(R.id.recyclerView), this, data!!)
    }


    fun update() {
        startUA()
        calculateHA()
    }


    //Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_leiste, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.datenSpeichern -> {
                BottomNavigation(this).datenSpeichern()
                true
            }
            R.id.datenZiehen -> {
                BottomNavigation(this).datenZiehen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // Menu bis hier hin

    fun swipeRefreshLayout() {
        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipe)
        swipe.setOnRefreshListener {
            update()
            swipe.isRefreshing = false
        }

    }

}