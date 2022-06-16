package com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.connecter.setitems

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.allgemein.DatePickerClass
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit

class SetItemEinkommen(view: View, val context: Context, val alertDialogMain: AlertDialogMain,
                       private val dataTransferUserAddedItem: DataTransferUserAddedItem) {

    //Hier wird reguliert was der User einzugeben hat
    // und es werden eventuelle fehler ausgeschlossen
    // Beispiel wenn der USer einen unterkonto,Ausgabe,Einkommen hinzufügen wird


    var etEinkommen: EditText = view.findViewById(R.id.setItemEinkommenEinkommenEt)
    var etDatum: EditText = view.findViewById(R.id.setItemEinkommenDatumEt)
    var etBeschreibung: EditText = view.findViewById(R.id.setItemEinkommenBeschreibungEt)
    var speichern: Button = view.findViewById(R.id.setItemEinkommenSpeichern)
    val datePickerClass = DatePickerClass(context, etDatum)

    private val sqliteInit = SQliteInit(context)
    private lateinit var model : EinkommenModel

    fun init() {
        clickDatum()
        clickSpeichern()




    }
    fun clickDatum(){
        etDatum.setText(datePickerClass.getTodaysDate())
        etDatum.setOnClickListener {
            datePickerClass.initDatePicker()
        }
    }
    fun clickSpeichern(){
        speichern.setOnClickListener {
            var einkommen = etEinkommen.text.toString()
            var beschreibung = etBeschreibung.text.toString()
            var datum = etDatum.text.toString()
            if (datum.isNotEmpty() && einkommen.isNotEmpty()) {
                println(einkommen)
                model = EinkommenModel(einkommen,datum,"einkommen","",beschreibung)
                sqliteInit.einnahme().setData(model)
                alertDialogMain.cancelDialog()
                dataTransferUserAddedItem.data(true)
            } else {
                Toast.makeText(context,"Lasse Einkommen und Datum nicht leer.",Toast.LENGTH_SHORT).show()
            }

        }
    }

}