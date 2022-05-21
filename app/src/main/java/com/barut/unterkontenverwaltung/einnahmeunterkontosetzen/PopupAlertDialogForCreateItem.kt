package com.barut.unterkontenverwaltung.einnahmeunterkontosetzen

import android.content.Context
import android.view.View
import android.widget.Button
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PopupAlertDialogForCreateItem(private val context : Context, val add : FloatingActionButton) {



    /*

    Hier wird der AlertDialog aufgerufen wenn der Plus button geklickt wird wo der User nochmal zwei
    möglichkeiten hat. Entweder Unterkonto oder Einnahme Button zu klicken! jenachdem was er auswählt wird
    die Auswahl per Interface in die Main klassen weitergegegeben!


     */


    private lateinit var btUnterkonto : Button
    private lateinit var btEinnahme : Button

    fun setAlertDialogForSetUnterkontoOrEinnahme(getclick : TransferDataFromPopupToSetItem){
        add.setOnClickListener  {

            val popUpAlertDialog = AlertDialogMain(context, R.layout.popup_alert_dialog_for_create_item)
            val view = popUpAlertDialog.setLayout()
            popUpAlertDialog.createDialog()
            initViews(view)
            onClickListener(getclick)


        }

    }

    fun onClickListener(getclick : TransferDataFromPopupToSetItem){


        btEinnahme.setOnClickListener {
            val addItem = AlertDialogMain(context,R.layout.add_item)
            val view = addItem.setLayout()
            addItem.createDialog()
            getclick.getClick(1, view,addItem)
        }
        btUnterkonto.setOnClickListener {
            val addItem = AlertDialogMain(context,R.layout.add_item)
            val view = addItem.setLayout()
            addItem.createDialog()
            getclick.getClick(2,view,addItem)
        }

    }
    private fun initViews(view : View){
        btUnterkonto = view.findViewById(R.id.btsetUnterkonto)
        btEinnahme = view.findViewById(R.id.btsetGeld)

    }
}