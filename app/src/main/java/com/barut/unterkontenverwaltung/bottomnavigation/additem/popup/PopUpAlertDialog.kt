package com.barut.unterkontenverwaltung.bottomnavigation.additem.popup

import android.content.Context
import android.view.View
import android.widget.Button
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.DataTransferPopUp
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain

class PopUpAlertDialog(private val context : Context) {



    /*

    Hier wird der AlertDialog aufgerufen wenn der Plus button geklickt wird wo der User nochmal zwei
    möglichkeiten hat. Entweder Unterkonto oder Einnahme Button zu klicken! jenachdem was er auswählt wird
    die Auswahl per Interface in die Main klassen weitergegegeben!


     */


    private lateinit var btUnterkonto : Button
    private lateinit var btEinnahme : Button
    private lateinit var btAusgabe : Button

    fun init(){


            val popUpAlertDialog = AlertDialogMain(context, R.layout.popup_alert_dialog_for_3_moeglichkeiten)
            val view = popUpAlertDialog.inflateLayout()
            popUpAlertDialog.createDialog()
            initViews(view)





    }

    fun onClickListener(getclick : DataTransferPopUp){


        btEinnahme.setOnClickListener {
            val addItem = AlertDialogMain(context,R.layout.set_item_einkommen)
            val view = addItem.inflateLayout()
            addItem.createDialog()
            getclick.getData(1, view,addItem)
        }
        btUnterkonto.setOnClickListener {
            val addItem = AlertDialogMain(context,R.layout.set_item_unterkonto)
            val view = addItem.inflateLayout()
            addItem.createDialog()
            getclick.getData(2,view,addItem)
        }
        btAusgabe.setOnClickListener {
            val addItem = AlertDialogMain(context,R.layout.set_item_ausgaben)
            val view = addItem.inflateLayout()
            addItem.createDialog()
            getclick.getData(3,view,addItem)
        }

    }
    private fun initViews(view : View){
        btUnterkonto = view.findViewById(R.id.btsetUnterkonto)
        btEinnahme = view.findViewById(R.id.btsetGeld)
        btAusgabe = view.findViewById(R.id.btAusgabeHinzufugen)

    }
}