package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.calculate.Calculate
import com.barut.unterkontenverwaltung.action.GetData
import com.barut.unterkontenverwaltung.action.TransferDataFromPopupToSetItem
import com.barut.unterkontenverwaltung.action.PopupAlertDialogForCreateItem
import com.barut.unterkontenverwaltung.action.SetItem
import com.barut.unterkontenverwaltung.json.Json
import com.barut.unterkontenverwaltung.json.MapModelForJson
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var sqLiteMainEinkommen: SQLiteMain
    private lateinit var sqLiteMainUnterkonto: SQLiteMain
    private lateinit var sqLiteMainAusgabe: SQLiteMain
    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigation: BottomNavigationView


    //731d4936dc48b1b6dd0ae921794b791b3bc8c3d3
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initalisiert alle views die an die verschiedenne klassen weiter gegeben werden!
        initAllViews()
        //Plus button wird geklickt! User darf sich aussuchen unterkonto oder Einnahme hinzufügen. je nachdem was er ausgewählt hat
        //wird seine eingaben im unterkonto oder im Einkommen sqlite datenbank gespeichert!

        //popUpAlertDialogForSetDataInSQLite()
        bottomNavigationClickListener()
        showCalculateDataInRecyclerView()
        showScollViewData()
        Json()

    }

    fun showScollViewData() {
        var calculate = Calculate(sqLiteMainEinkommen, sqLiteMainUnterkonto, sqLiteMainAusgabe)
        var data = calculate.calculate()

        val tvGesamtSaldo: TextView = findViewById(R.id.tvGesamtSaldo2Ausgabe)
        val tvGesamtAusgaben: TextView = findViewById(R.id.tvgesamtAusgabenAusgabe)
        val tvVerfugbarerSaldo: TextView = findViewById(R.id.tvVerfugbaererSaldoAusgabe)
        val tvUnterkontenAnzahl: TextView = findViewById(R.id.tvUnterkontenAnzahlAusgabe)
        val tvProzentAnzahl: TextView = findViewById(R.id.tvProzentAnzahlAusgabe)
        val tvBeschreibung: TextView = findViewById(R.id.tvBeschreibungAusgabe)


        tvGesamtSaldo.setText("${runden(data.gesamtSaldo)}€")
        tvGesamtAusgaben.setText("${runden(data.gesamtAusgaben)}€")
        tvVerfugbarerSaldo.setText("${runden(data.verfugbarerSaldo)}€")
        tvUnterkontenAnzahl.setText("${runden(data.unterKontenAnzahl)}")
        tvProzentAnzahl.setText("${runden(data.prozenteGesamt)}%")
        tvBeschreibung.setText(data.beschreibungVorhandenODerNicht)
    }

    fun showCalculateDataInRecyclerView() {
        var calculate = Calculate(sqLiteMainEinkommen, sqLiteMainUnterkonto, sqLiteMainAusgabe)
        var newinhalt = calculate.calculateData()
        val inhalt = getInhalt()
        StartRecyclerView(
            this, recyclerView,
            inhalt, R.layout.end_model_show_datas_in_recyclerview, "EndShowDataCalculate",
            null, newinhalt!!
        )
    }


    fun popUpAlertDialogForSetDataInSQLite() {
        val popUp =
            PopupAlertDialogForCreateItem(this).setAlertDialogForSetUnterkontoOrEinnahme(object :
                TransferDataFromPopupToSetItem {
                override fun getClick(klick: Int, view: View, alertdialog: AlertDialogMain) {
                    SetItem(
                        klick,
                        this@MainActivity,
                        view,
                        sqLiteMainUnterkonto.readData()
                    ).getData(object : GetData {
                        override fun getData(model: Model) {
                            if (model.databaseType == "Unterkonto") {
                                alertdialog.cancelDialog()
                                sqLiteMainUnterkonto.setData(model)
                                Toast.makeText(
                                    this@MainActivity,
                                    "erfolgreich hinzugefügt",
                                    Toast.LENGTH_LONG
                                ).show()
                                update()

                            } else if (model.databaseType == "Einnahme") {
                                alertdialog.cancelDialog()
                                sqLiteMainEinkommen.setData(model)
                                Toast.makeText(
                                    this@MainActivity,
                                    "erfolgreich hinzugefügt",
                                    Toast.LENGTH_LONG
                                ).show()
                                update()

                            } else if (model.databaseType == "Ausgabe") {
                                alertdialog.cancelDialog()
                                sqLiteMainAusgabe.setData(model)
                                Toast.makeText(
                                    this@MainActivity,
                                    "erfolgreich hinzugefügt",
                                    Toast.LENGTH_LONG
                                ).show()
                                update()
                            }

                        }
                    })


                }

            })

    }

    fun bottomNavigationClickListener() {
        bottomNavigation.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.home) {
                    update()
                    return true
                } else if (item.itemId == R.id.liste) {
                    update()
                    showItemsInRecyclerView()
                    return true
                } else if (item.itemId == R.id.addItem) {
                    showScollViewData()
                    popUpAlertDialogForSetDataInSQLite()
                    return true
                }
                return false
            }

        })
    }

    fun getInhalt(): ArrayList<Model> {

        val dataEinkommen = sqLiteMainEinkommen.readData()
        val dataUnterkonto = sqLiteMainUnterkonto.readData()
        val dataAusgabe = sqLiteMainAusgabe.readData()


        val arraylist: ArrayList<Model> = arrayListOf()

        for (i in dataEinkommen) {
            arraylist.add(i)
        }
        for (i in dataUnterkonto) {
            arraylist.add(i)
        }
        for (i in dataAusgabe) {
            arraylist.add(i)
        }
        arraylist.sortWith(compareBy({ it.databaseType }, { it.datum }))
        return arraylist
    }

    fun showItemsInRecyclerView() {

        val arraylist = getInhalt()
        StartRecyclerView(
            this, recyclerView,
            arraylist, R.layout.show_items, "ShowItems", null, null
        )
    }

    fun update() {
        showScollViewData()
        showCalculateDataInRecyclerView()
    }

    fun initAllViews() {
        sqLiteMainEinkommen = SQLiteMain(
            this@MainActivity, "Einkommen", "Einkommen",
            "unterkonto", "datum", "echtZeitDatum", "databaseType", "id", "beschreibung"
        )
        sqLiteMainUnterkonto = SQLiteMain(
            this@MainActivity, "Unterkonto", "Unterkonto",
            "name", "prozent", "datum", "databaseType", "id", "beschreibung"
        )
        sqLiteMainAusgabe = SQLiteMain(
            this@MainActivity, "Ausgabe", "Ausgabe",
            "unterkonto", "ausgabe", "datum", "databaseType", "id", "beschreibung"
        )

        recyclerView = findViewById(R.id.recyclerView)
        bottomNavigation = findViewById(R.id.bottomNavigation)


    }

    fun runden(double: Double): Double {
        var roundOff = (double * 100.0).roundToInt() / 100.0
        return roundOff
    }


}