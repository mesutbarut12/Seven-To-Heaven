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
    private var datePickerDialog: DatePickerDialog? = null



    fun getData(getData: GetData) {

        tvDescription3.setText("Bitte gebe deine Beschreibung ein!")
        tvDescription4.setText("Datum Wählen!")
        input4.setText(getTodaysDate())
        input4.setOnClickListener {
            datePicker()
            datePickerDialog!!.show()
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
            if (input1.text.isEmpty() || input2.text.isEmpty()) {
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
                    databaseTyp, "", input3.text.toString()
                )
            }
        } else {

            model = Model(
                input1.text.toString(),
                input2.text.toString(),
                "Tag der erstellung ${dateFormat.format(date)}",
                databaseTyp, "", input3.text.toString()
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
                    databaseTyp, "", input3.text.toString()
                )
            }
        }
        return model
    }

    fun datePicker() {
        initDatePicker()
    }

    private fun initDatePicker() {
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String = makeDateString(day, month, year)
                input4!!.setText(date)
            }
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val style = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(context, style, dateSetListener, year, month, day)
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private fun getTodaysDate(): String? {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        month = month + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        return makeDateString(day, month, year)
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return "${day}.${getMonthFormat(month)}.${year}"
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "1"
        if (month == 2) return "2"
        if (month == 3) return "3"
        if (month == 4) return "4"
        if (month == 5) return "5"
        if (month == 6) return "6"
        if (month == 7) return "7"
        if (month == 8) return "8"
        if (month == 9) return "9"
        if (month == 10) return "10"
        if (month == 11) return "11"
        return if (month == 12) "12" else "1"

        //default should never happen
    }
    interface GetData {
        fun getData(model: Model)
    }
}

