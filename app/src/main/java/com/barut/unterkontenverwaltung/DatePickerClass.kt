package com.barut.unterkontenverwaltung

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.util.*

class DatePickerClass(val context: Context,val input : EditText) {
     var datePickerDialog: DatePickerDialog? = null

     fun initDatePicker() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String = makeDateString(day, month, year)
                input.setText(date)
            }
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val style = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(context, style, dateSetListener, year, month, day)
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
         datePickerDialog!!.show()
    }

     fun getTodaysDate(): String? {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        month = month + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        return makeDateString(day, month, year)
    }

     fun makeDateString(day: Int, month: Int, year: Int): String {
        return "${day}.${getMonthFormat(month)}.${year}"
    }

     fun getMonthFormat(month: Int): String {
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
}