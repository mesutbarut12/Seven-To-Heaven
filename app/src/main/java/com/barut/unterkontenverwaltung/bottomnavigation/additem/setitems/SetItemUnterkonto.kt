package com.barut.unterkontenverwaltung.bottomnavigation.additem.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.barut.unterkontenverwaltung.DatePickerClass
import com.barut.unterkontenverwaltung.R

class SetItemUnterkonto(view: View,val context: Context) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird



    var unterkonto: EditText = view.findViewById(R.id.setItemUnterkontoUnterkontoEt)
    var etDatum: EditText = view.findViewById(R.id.setItemUnterkontoDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemUnterkontoBeschreibungEt)
    var etprozent: EditText = view.findViewById(R.id.setItemUnterkontoProzentEt)
    var speichern: Button = view.findViewById(R.id.setItemUnterkontoSpeichern)

    val datePickerClass = DatePickerClass(context, etDatum)


    fun init() {

        var einkommen = unterkonto.text.toString()
        var beschreibung = etBeschreibung.text.toString()
        var datum = etDatum.text.toString()
        var prozent = etprozent.text.toString()

        etDatum.setText(datePickerClass.getTodaysDate())
        etDatum.setOnClickListener {
            datePickerClass.initDatePicker()
        }
        speichern.setOnClickListener {
            if (datum.isNotEmpty() && einkommen.isNotEmpty() && prozent.isNotEmpty()) {

            } else {
                Toast.makeText(context,"Lasse Einkommen Datum und Prozent nicht leer.", Toast.LENGTH_SHORT).show()
            }

        }

    }

}