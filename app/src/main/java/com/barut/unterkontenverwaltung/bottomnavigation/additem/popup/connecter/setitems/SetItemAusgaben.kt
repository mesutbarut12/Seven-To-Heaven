package com.barut.unterkontenverwaltung.bottomnavigation.additem.popup.connecter.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.barut.unterkontenverwaltung.DataTransferShowUnterkontoBinder
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.DatePickerClass
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.calculate.hauptanzeige.CalculateHauptAnzeige
import com.barut.unterkontenverwaltung.calculate.uebersichtsanzeige.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel
import com.barut.unterkontenverwaltung.recyclerview.alleunterkonten.AURecyclerViewModel
import com.barut.unterkontenverwaltung.recyclerview.alleunterkonten.AUStartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQliteInit

class SetItemAusgaben(
    val view: View, val context: Context, val alertDialogMain: AlertDialogMain,
    private val dataTransferUserAddedItem: DataTransferUserAddedItem
) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird


    var etausgabe: EditText = view.findViewById(R.id.setItemAusgabeAusgabeEt)
    var etDatum: EditText = view.findViewById(R.id.setItemAusgabeDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemAusgabeBeschreibungEt)
    var etUnterkonto: EditText = view.findViewById(R.id.setItemAusgabeUnterkontoEt)
    var speichern: Button = view.findViewById(R.id.setItemAusgabenSpeichern)

    val datePickerClass = DatePickerClass(context, etDatum)

    var sqliteInit = SQliteInit(view.context)

    val alert = AlertDialogMain(context, R.layout.set_item_recyclerview_unterkonten)


    fun init() {
        clickDatum()
        clickSpeichern()
        clickUnterkonto()


    }

    fun clickDatum() {
        etDatum.setText(datePickerClass.getTodaysDate())
        etDatum.setOnClickListener {
            datePickerClass.initDatePicker()
        }
    }

    fun clickSpeichern() {
        speichern.setOnClickListener {
            var ausgabe1 = etausgabe.text.toString()
            var beschreibung = etBeschreibung.text.toString()
            var datum = etDatum.text.toString()
            var unterkonto = etUnterkonto.text.toString()

            if (ausgabe1.isNotEmpty() && datum.isNotEmpty() && unterkonto.isNotEmpty()) {
                var ausgabe = sqliteInit.ausgabe()
                var model = AusgabenModel(unterkonto, ausgabe1, datum, "ausgabe", "", beschreibung)
                ausgabe.setData(model)
                alertDialogMain.cancelDialog()
                dataTransferUserAddedItem.data(true)
            } else {
                Toast.makeText(
                    context,
                    "Lasse Einkommen Datum und Unterkonto nicht leer.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    fun getUnterkontoAndSumme(): AURecyclerViewModel {
        val arraylistName: ArrayList<String> = arrayListOf()
        val arraylistSumme: ArrayList<String> = arrayListOf()
        val model: AURecyclerViewModel

        var calculateHauptAnzeige = CalculateHauptAnzeige(context)
        calculateHauptAnzeige.init()
        var guthaben = calculateHauptAnzeige.hAGuthaben()

        for (i in guthaben) {
            println(i + " Guthaben")
            var unterkonto = i.split(".")[1]
            var guthaben = i.split(".")[0].toDouble()
            var ergebnis = 0.0
            for (z in sqliteInit.ausgabe().readData()) {
                println(z.unterkonto + " Ausgaben")
                if (unterkonto == z.unterkonto ) {
                    ergebnis = guthaben - z.summe.toDouble()
                    println(ergebnis.toString() + " Ergebnis")

                }
            }
            arraylistSumme.add(ergebnis.toString())
            arraylistName.add(unterkonto)

        }
        model = AURecyclerViewModel(arraylistName, arraylistSumme)

        return model
    }

    fun clickUnterkonto() {
        etUnterkonto.setOnClickListener {
            if (getUnterkontoAndSumme().unterkonto.isNotEmpty()) {
                val view1 = alert.setLayout()
                AUStartRecyclerView(
                    view1.findViewById(R.id.setItemRecyclerViewUnterkonten),
                    context,
                    getUnterkontoAndSumme(), dataTransferInterface()
                )
                alert.createDialog()
            } else {
                Toast.makeText(context,"Du hast keine verfügbaren Unterkonten!",
                Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun dataTransferInterface(): DataTransferShowUnterkontoBinder {
        val dataTransferShowUnterkontoBinder = object : DataTransferShowUnterkontoBinder {
            override fun getData(name: String) {
                val unterkonto = view.findViewById<TextView>(R.id.setItemAusgabeUnterkontoEt)
                unterkonto.setText(name)
                alert.cancelDialog()
            }
        }
        return dataTransferShowUnterkontoBinder
    }
}