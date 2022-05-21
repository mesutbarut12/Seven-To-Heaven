package com.barut.unterkontenverwaltung.einnahmeunterkontosetzen

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel

class SetItem(val klick : Int,val context : Context,val view : View)
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
            input1.setHint("bitte Einnahme eingeben!")
            tvDescription2.setText("Datum : ")
            input2.setHint("bitte Datum eingeben!")
            databaseTyp = "Einnahme"

        } else if(klick == 2){

            tvDescription1.setText("Unterkonto : ")
            input1.setHint("bitte Unterkonto eingeben!")
            tvDescription2.setText("Prozent : ")
            input2.setHint("bitte Prozent eingeben!")
            databaseTyp = "Unterkonto"

        }else {
            Toast.makeText(context,"Fehler beim Laden",Toast.LENGTH_LONG).show()
        }


        safe.setOnClickListener{
            if(input1.text.isEmpty() || input2.text.isEmpty()){
                Toast.makeText(context,"Lasse kein Feld leer stehen",Toast.LENGTH_LONG).show()
            } else {
                model = SQLiteModel(input1.text.toString(),input2.text.toString(),databaseTyp)
                Toast.makeText(context,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()
                getData.getData(model)
            }
        }

    }

    }

interface GetData{
    fun getData(model: SQLiteModel)
}