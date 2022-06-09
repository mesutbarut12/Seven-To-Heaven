package com.barut.unterkontenverwaltung.bottomnavigation.additem.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.barut.unterkontenverwaltung.DatePickerClass
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel
import com.barut.unterkontenverwaltung.sqlite.SQliteInit

class SetItemAusgaben(val view: View, val context: Context) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird


    var etausgabe: EditText = view.findViewById(R.id.setItemAusgabeAusgabeEt)
    var etDatum: EditText = view.findViewById(R.id.setItemAusgabeDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemAusgabeBeschreibungEt)
    var etUnterkonto: EditText = view.findViewById(R.id.setItemAusgabeUnterkontoEt)
    var speichern: Button = view.findViewById(R.id.setItemAusgabenSpeichern)

    val datePickerClass = DatePickerClass(context, etDatum)


    fun init() {
        var ausgabe1 = etausgabe.text.toString()
        var beschreibung = etBeschreibung.text.toString()
        var datum = etDatum.text.toString()
        var unterkonto = etUnterkonto.text.toString()

        etDatum.setText(datePickerClass.getTodaysDate())
        etDatum.setOnClickListener {
            datePickerClass.initDatePicker()
        }
        speichern.setOnClickListener {

                var sqliteInit = SQliteInit(view.context)
                var ausgabe = sqliteInit.ausgabe()
                var model = AusgabenModel("a", ausgabe1, datum, "ausgabe", "", beschreibung)
                ausgabe.setData(model)

                Toast.makeText(
                    context,
                    "Lasse Einkommen Datum und Unterkonto nicht leer.",
                    Toast.LENGTH_SHORT
                ).show()


        }
    }

}