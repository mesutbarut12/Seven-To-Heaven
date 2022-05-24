package com.barut.unterkontenverwaltung.einnahmeunterkontosetzen

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.EinigeChecksFürBessereSicherheit
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontenInRecyclerView
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontenInRecyclerViewBinder
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SetItem(private val klick : Int,private val context : Context,private val view : View)
{
    private val tvDescription1 : TextView = view.findViewById(R.id.tvdescription1)
    private val tvDescription2 : TextView = view.findViewById(R.id.tvdescription2)
    private val input1 : EditText = view.findViewById(R.id.etInput1)
    private val input2 : EditText = view.findViewById(R.id.etInput2)
    private val safe : Button = view.findViewById(R.id.btSaveItems)
    private lateinit var model : SQLiteModel



    fun getData(getData: GetData) {
        var databaseTyp = ""


        if(klick == 1){

            tvDescription1.setText("Einnahme : ")
            input1.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input1.setHint("bitte Einnahme eingeben!")
            tvDescription2.setText("Datum : ")
            input2.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input2.setHint("bitte Datum eingeben!")
            databaseTyp = "Einnahme"

        } else if(klick == 2){

            tvDescription1.setText("Unterkonto : ")
            input1.setHint("bitte Unterkonto eingeben!")
            tvDescription2.setText("Prozent : ")
            input2.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input2.setHint("bitte Prozent eingeben!")
            databaseTyp = "Unterkonto"

        }else if(klick == 3){
            tvDescription1.setText("Ausgabe : ")
            input1.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input1.setHint("bitte Ausgabe eingeben!")
            tvDescription2.setText("Unterkonto : ")
            input2.setHint("bitte Unterkonto eingeben!")
            input2
            databaseTyp = "Ausgabe"
            val alert = AlertDialogMain(context,R.layout.recyclerview_showallexistsunterkonten)
            val view = alert.setLayout()
            val recyclerView : RecyclerView = view.findViewById(R.id.rvShowAllExistingUnterkonten)
            alert.createDialog()
            ShowExistingUnterkontenInRecyclerView(context,recyclerView,object : ShowExistingUnterkontoInterface{
                override fun showExistingUnterkonto(boolean: Boolean, data: String) {
                    if(boolean == true){
                        alert.cancelDialog()
                        input2.setText(data)

                    }
                }

            }).onStart()
        } else {
            Toast.makeText(context,"Fehler beim Laden",Toast.LENGTH_LONG).show()
        }

        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy ")
        val date = Date()

        safe.setOnClickListener {
            if (input1.text.isEmpty() || input2.text.isEmpty()) {
                Toast.makeText(context, "Lasse kein Feld leer stehen", Toast.LENGTH_LONG).show()
            } else if (input1.text.contains(",")) {
                val newInput1 =
                    EinigeChecksFürBessereSicherheit().checkUserPutKommaOrPunkt(input1.text.toString())
                model = SQLiteModel(
                    newInput1,
                    input2.text.toString(),
                    "Tag der erstellung ${dateFormat.format(date)}",
                    databaseTyp, "")
                getData.getData(model)
            } else if (input2.text.contains(",")) {
                val newInput1 =
                    EinigeChecksFürBessereSicherheit().checkUserPutKommaOrPunkt(input2.text.toString())

                model = SQLiteModel(
                    input1.text.toString(),
                    newInput1,
                    "Tag der erstellung ${dateFormat.format(date)}",
                    databaseTyp,"")
                getData.getData(model)


            } else {
                model = SQLiteModel(
                    input1.text.toString(),
                    input2.text.toString(),
                    "Tag der erstellung ${dateFormat.format(date)}",
                    databaseTyp, "")
                getData.getData(model)
            }
        }
    }

}




interface GetData{
    fun getData(model: SQLiteModel)
}