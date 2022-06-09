package com.barut.unterkontenverwaltung.bottomnavigation.additem.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.barut.unterkontenverwaltung.DatePickerClass
import com.barut.unterkontenverwaltung.R

class SetItemEinkommen(view: View, val context: Context) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird


    var etEinkommen: EditText = view.findViewById(R.id.setItemEinkommenEinkommenEt)
    var etDatum: EditText = view.findViewById(R.id.setItemEinkommenDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemEinkommenBeschreibungEt)
    var speichern: Button = view.findViewById(R.id.setItemEinkommenSpeichern)
    val datePickerClass = DatePickerClass(context, etDatum)


    fun init() {
        var einkommen = etEinkommen.text.toString()
        var beschreibung = etBeschreibung.text.toString()
        var datum = etDatum.text.toString()

        etDatum.setText(datePickerClass.getTodaysDate())
        etDatum.setOnClickListener {
            datePickerClass.initDatePicker()
        }

        speichern.setOnClickListener {
            if (datum.isNotEmpty() && einkommen.isNotEmpty()) {

            } else {
                Toast.makeText(context,"Lasse Einkommen und Datum nicht leer.",Toast.LENGTH_SHORT).show()
            }

        }
    }

}