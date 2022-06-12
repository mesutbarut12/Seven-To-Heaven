package com.barut.unterkontenverwaltung.mainactivity.bottomnavigation.additem.popup.connecter.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.allgemein.DatePickerClass
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class SetItemUnterkonto(
    view: View, val context: Context, val alertDialogMain: AlertDialogMain, private val
    dataTransferUserAddedItem: DataTransferUserAddedItem
) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird


    var unterkonto: EditText = view.findViewById(R.id.setItemUnterkontoUnterkontoEt)
    var etDatum: EditText = view.findViewById(R.id.setItemUnterkontoDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemUnterkontoBeschreibungEt)
    var etprozent: EditText = view.findViewById(R.id.setItemUnterkontoProzentEt)
    var speichern: Button = view.findViewById(R.id.setItemUnterkontoSpeichern)

    lateinit var unterkontoName: String
    lateinit var beschreibung: String
    lateinit var datum: String
    lateinit var prozent: String


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

            initViewsToString()
            var unterkontoVorhanden = unterkontoVorhanden()
            var prozentSumme = prozentSumme()

            if (datum.isNotEmpty() && unterkontoName.isNotEmpty() && prozent.isNotEmpty() &&
                unterkontoVorhanden == false && prozentSumme == false
            ) {
                model = UnterkontoModel(
                    unterkontoName, prozent, datum, "unterkonto", "",
                    beschreibung
                )
                sQliteInit.unterkonto().setData(model)
                alertDialogMain.cancelDialog()
                dataTransferUserAddedItem.data(true)

            } else {
                Toast.makeText(
                    context,
                    "Fehler aufgetreten!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun initViewsToString(){
        unterkontoName = unterkonto.text.toString()
        beschreibung = etBeschreibung.text.toString()
        datum = etDatum.text.toString()
        prozent = etprozent.text.toString()
    }
    private fun unterkontoVorhanden(): Boolean {

        for (i in sQliteInit.unterkonto().readData()) {
            if (i.name == unterkontoName) {
                return true
            }
        }
        return false
    }
    private fun prozentSumme() : Boolean{

        var calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()
        var prozentGesamt = calculateUebersichtsAnzeige.uAProzenteGesamt()
        var ergebnis = prozent.toDouble() + prozentGesamt.toDouble()
        if(ergebnis > 100){
            return true
        }
        return false
    }
}