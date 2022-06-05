package com.barut.unterkontenverwaltung

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.calculate.Calculate
import com.barut.unterkontenverwaltung.action.TransferDataFromPopupToSetItem
import com.barut.unterkontenverwaltung.action.PopupAlertDialogForCreateItem
import com.barut.unterkontenverwaltung.action.SetItem
import com.barut.unterkontenverwaltung.json.DataFinish
import com.barut.unterkontenverwaltung.json.Json
import com.barut.unterkontenverwaltung.json.UserIDExistsInterface
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var sqLiteMainEinkommen: SQLiteMain
    private lateinit var sqLiteMainUnterkonto: SQLiteMain
    private lateinit var sqLiteMainAusgabe: SQLiteMain
    private lateinit var sqLiteUserId: SQLiteMain
    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var json: Json


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
        giveUserId()


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
                    ).getData(object : SetItem.GetData {
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
                } else if (item.itemId == R.id.datenSpeichern) {
                    setAlertDialogForSaveAndGetFirebase("Speichern?", "Möchten Du " +
                            "die jetzigen Daten speichern?", object : AlertDialogClick {
                        override fun onClickListener(boolean: Boolean) {
                            if (boolean == true) {
                                saveDataInFirestore()

                            } else if (boolean == false) {
                                Toast.makeText(
                                    this@MainActivity, "Erfolgreich abgebrochen",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                    return true
                } else if (item.itemId == R.id.datenUpdate) {
                    setAlertDialogForSaveAndGetFirebase(
                        "Daten ziehen?",
                        "Möchten Du " +
                                "wirklich den letzten speicher ziehen? ",
                        object : AlertDialogClick {
                            override fun onClickListener(boolean: Boolean) {
                                if (boolean == true) {
                                    val alert = AlertDialogMain(
                                        this@MainActivity, R.layout.daten_ziehen
                                    )
                                    val view = alert.setLayout()
                                    val buttonId = view.findViewById<Button>(R.id.datenId)
                                    val buttonIdEigene = view.findViewById<Button>(R.id.datenIdeigene)
                                    val etId = view.findViewById<EditText>(R.id.editTextId)
                                    buttonId.setOnClickListener {
                                        if (etId.text.isNotEmpty()) {
                                            val userId = etId.text.toString()
                                            getDataFromFirebase(userId)
                                            alert.cancelDialog()
                                        }
                                    }
                                    buttonIdEigene.setOnClickListener {
                                        etId.setText(getUserId())
                                    }
                                    alert.createDialog()
                                } else if (boolean == false) {
                                    Toast.makeText(
                                        this@MainActivity, "Erfolgreich abgebrochen",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    )
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
        sqLiteUserId = SQLiteMain(
            this@MainActivity, "UserId", "UserId",
            "userId", "nichtbenutzt1", "nichtbenutzt2", "nichtbenutzt3", "id", "nichtbenutzt4"
        )


        recyclerView = findViewById(R.id.recyclerView)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        json = Json(this, sqLiteMainEinkommen, sqLiteMainAusgabe, sqLiteMainUnterkonto)

    }

    fun runden(double: Double): Double {
        var roundOff = (double * 100.0).roundToInt() / 100.0
        return roundOff
    }


    fun giveUserId() {
        val userId = UUID.randomUUID()
        if (sqLiteUserId.readData().isEmpty()) {
            sqLiteUserId.setData(
                Model(
                    userId.toString(), "", "", "", "", ""
                )
            )
        }

    }

    fun deleteUserId() {
        for (i in sqLiteUserId.readData()) {
            sqLiteUserId.deleateItem(i.spaltenName1, "")
        }
    }

    fun getUserId(): String {
        for (i in sqLiteUserId.readData()) {
            return i.spaltenName1
        }
        return "Fehler"
    }

    fun saveDataInFirestore() {
        val hashMap: HashMap<String, ArrayList<Model>> = hashMapOf()
        hashMap.set("Einkommen", sqLiteMainEinkommen.readData())
        hashMap.set("Ausgabe", sqLiteMainAusgabe.readData())
        hashMap.set("Unterkonto", sqLiteMainUnterkonto.readData())
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        json.save(hashMap, getUserId(), object : DataFinish {
            override fun finish(boolean: Boolean) {
                if (boolean == true) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(
                        this@MainActivity,
                        "Daten Erfolgreich Gespeichert!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })

    }

    fun getDataFromFirebase(userId: String) {
        var getUserId = getUserId()

        if (userId.isNotEmpty()) {
            getUserId = userId
        }

        println(getUserId)
        json.userIdIsExists(getUserId, object : UserIDExistsInterface {
            override fun getData(boolean: Boolean) {
                if (boolean == true) {
                    getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                    json.getData(getUserId, object : DataFinish {
                        override fun finish(boolean: Boolean) {
                            if (boolean == true) {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                Toast.makeText(
                                    this@MainActivity,
                                    "Daten Erfolgreich übernommen!", Toast.LENGTH_LONG
                                ).show()

                            } else {
                                Toast.makeText(this@MainActivity, "Die datei ist leer", Toast.LENGTH_LONG)
                                    .show()
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        }
                    })

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Die Id gibt es nicht!", Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

    }

    fun setAlertDialogForSaveAndGetFirebase(
        titel: String,
        message: String,
        alertDialogClick: AlertDialogClick
    ) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle(titel)
        alert.setMessage(message)
        alert.setPositiveButton("BESTÄTIGEN", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                alertDialogClick.onClickListener(true)
            }
        }).setNegativeButton("Abbrechen", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                alertDialogClick.onClickListener(false)
            }
        })
        alert.create().show()


    }


}