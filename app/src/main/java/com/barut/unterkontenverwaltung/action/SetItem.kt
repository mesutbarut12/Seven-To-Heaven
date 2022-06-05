package com.barut.unterkontenverwaltung.action

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DatePickerClass
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SetItem(
    private val klick: Int, private val context: Context, private val view: View,
    val dataUnterkonto: ArrayList<Model>
) {
    private val tvDescription1: TextView = view.findViewById(R.id.tvdescription1)
    private val tvDescription2: TextView = view.findViewById(R.id.tvdescription2)
    private val tvDescription3: TextView = view.findViewById(R.id.tvdescription3)
    private val tvDescription4: TextView = view.findViewById(R.id.tvdescription4)
    private val input1: EditText = view.findViewById(R.id.etInput1)
    private val input2: EditText = view.findViewById(R.id.etInput2)
    private val input3: EditText = view.findViewById(R.id.etInput3)
    private val input4: EditText = view.findViewById(R.id.etInput4)
    private val safe: Button = view.findViewById(R.id.btSaveItems)
    private lateinit var model: Model
    var databaseTyp = ""

    //datepicker
    val datePickerClass  = DatePickerClass(context,input4)



    fun getData(getData: GetData) {

        tvDescription3.setText("Bitte gebe deine Beschreibung ein!")
        tvDescription4.setText("Datum Wählen!")
        input4.setText(datePickerClass.getTodaysDate())
        input4.setOnClickListener {
            datePickerClass.initDatePicker()

        }

        if (klick == 1) {

            tvDescription1.setText("Einnahme : ")
            input1.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input1.setHint("bitte Einnahme eingeben!")
            tvDescription2.setText("Datum : ")
            input2.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input2.setHint("bitte Datum eingeben!")
            databaseTyp = "Einnahme"

        } else if (klick == 2) {

            tvDescription1.setText("Unterkonto : ")
            input1.setHint("bitte Unterkonto eingeben!")
            tvDescription2.setText("Prozent : ")
            input2.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input2.setHint("bitte Prozent eingeben!")
            databaseTyp = "Unterkonto"


        } else if (klick == 3) {
            tvDescription1.setText("Ausgabe : ")
            input1.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            input1.setHint("bitte Ausgabe eingeben!")
            tvDescription2.setText("Unterkonto : ")
            input2.setHint("bitte Unterkonto eingeben!")

            createNewAlterDialogForShowUnterkontenWhenKlickAusgabenHinzufugen()

        } else {
            Toast.makeText(context, "Fehler beim Laden", Toast.LENGTH_LONG).show()
        }
        whenUserKlickSafeButton(getData)
    }

    fun createNewAlterDialogForShowUnterkontenWhenKlickAusgabenHinzufugen() {

        databaseTyp = "Ausgabe"
        val alert = AlertDialogMain(context, R.layout.recyclerview_showallexistsunterkonten)
        val view = alert.setLayout()
        val recyclerView: RecyclerView = view.findViewById(R.id.rvShowAllExistingUnterkonten)
        alert.createDialog()

        StartRecyclerView(
            context,
            recyclerView,
            dataUnterkonto,
            R.layout.show_existing_unterkonten,
            "ShowExistingUnterkontenItems",
            object : ShowExistingUnterkontoInterface {
                override fun showExistingUnterkonto(boolean: Boolean, data: String) {
                    if (boolean == true) {
                        input2.setText(data)
                        alert.cancelDialog()
                    }
                }

            },
            null
        )

    }

    fun whenUserKlickSafeButton(getData: GetData) {

        safe.setOnClickListener {
            if (input1.text.isEmpty() || input2.text.isEmpty() || input4.text.isEmpty()) {
                Toast.makeText(context, "Lasse kein Feld leer stehen", Toast.LENGTH_LONG).show()

            } else {

                val prozentUber100OderNicht = checkProzentIsNot100(input2.text.toString())
                val nameExistiertBereitsOderNicht =
                    checkWhenUnterkontoNameDontExists(input1.text.toString())
                if (prozentUber100OderNicht != null && nameExistiertBereitsOderNicht != null) {
                    getData.getData(prozentUber100OderNicht!!)
                }

            }

        }
    }

    fun checkProzentIsNot100(prozentZahl: String): Model? {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy ")
        val date = Date()
        if (klick == 2) {
            var ergebnis = 0.0
            for (i in dataUnterkonto) {
                ergebnis += i.spaltenName2.toDouble()
            }
            ergebnis += prozentZahl.toDouble()

            if (ergebnis > 100.00) {
                Toast.makeText(
                    context,
                    "Dieser schritt wird die 100% grenze überschreiten bitte versuche es erneut!",
                    Toast.LENGTH_LONG
                ).show()
                return null

            } else {
                model = Model(
                    input1.text.toString(),
                    input2.text.toString(),
                    "Tag der erstellung ${dateFormat.format(date)}",
                    databaseTyp, "", input3.text.toString(),
                    input4.text.toString()
                    )
            }
        } else {

            model = Model(
                input1.text.toString(),
                input2.text.toString(),
                "Tag der erstellung ${dateFormat.format(date)}",
                databaseTyp, "", input3.text.toString(),
                input4.text.toString()
            )
        }
        return model
    }

    fun checkWhenUnterkontoNameDontExists(input: String): Model? {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy ")
        val date = Date()
        for (i in dataUnterkonto) {

            if (i.spaltenName1 == input) {
                Toast.makeText(context, "Der Name existiert bereits!!", Toast.LENGTH_LONG).show()
                return null
            } else {
                model = Model(
                    input1.text.toString(),
                    input2.text.toString(),
                    "Tag der erstellung ${dateFormat.format(date)}",
                    databaseTyp, "", input3.text.toString(),
                    input4.text.toString()
                )
            }
        }
        return model
    }

    interface GetData {
        fun getData(model: Model)
    }
}

