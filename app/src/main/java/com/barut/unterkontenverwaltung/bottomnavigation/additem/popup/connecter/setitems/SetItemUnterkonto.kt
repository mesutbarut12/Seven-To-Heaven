package com.barut.unterkontenverwaltung.bottomnavigation.additem.popup.connecter.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.barut.unterkontenverwaltung.DatePickerClass
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel
import com.barut.unterkontenverwaltung.sqlite.SQliteInit

class SetItemUnterkonto(view: View, val context: Context,val alertDialogMain: AlertDialogMain) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird


    var unterkonto: EditText = view.findViewById(R.id.setItemUnterkontoUnterkontoEt)
    var etDatum: EditText = view.findViewById(R.id.setItemUnterkontoDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemUnterkontoBeschreibungEt)
    var etprozent: EditText = view.findViewById(R.id.setItemUnterkontoProzentEt)
    var speichern: Button = view.findViewById(R.id.setItemUnterkontoSpeichern)

    val datePickerClass = DatePickerClass(context, etDatum)

    private val sQliteInit = SQliteInit(context)
    private lateinit var model: UnterkontoModel


    fun init() {
        clickDatum()
        clickSpeichern()

    }

    private fun clickDatum() {
        etDatum.setText(datePickerClass.getTodaysDate())
        etDatum.setOnClickListener {
            datePickerClass.initDatePicker()
        }
    }

    private fun clickSpeichern() {
        speichern.setOnClickListener {
            var unterkontoName = unterkonto.text.toString()
            var beschreibung = etBeschreibung.text.toString()
            var datum = etDatum.text.toString()
            var prozent = etprozent.text.toString()
            if (datum.isNotEmpty() && unterkontoName.isNotEmpty() && prozent.isNotEmpty()) {
                model = UnterkontoModel(
                    unterkontoName, prozent, datum, "unterkonto", "",
                    beschreibung
                )
                sQliteInit.unterkonto().setData(model)
                alertDialogMain.cancelDialog()
            } else {
                Toast.makeText(
                    context,
                    "Lasse Einkommen Datum und Prozent nicht leer.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

}