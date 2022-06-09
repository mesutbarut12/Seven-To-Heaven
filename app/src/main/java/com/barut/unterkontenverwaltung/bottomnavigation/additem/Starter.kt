package com.barut.unterkontenverwaltung.bottomnavigation.additem

import android.content.Context
import com.barut.unterkontenverwaltung.bottomnavigation.additem.connecter.Connecter
import com.barut.unterkontenverwaltung.bottomnavigation.additem.popup.PopUpAlertDialog

class Starter(val context : Context) {
    private lateinit var popUpAlertDialog: PopUpAlertDialog
    private lateinit var connecter: Connecter

    fun init(){
         popUpAlertDialog = PopUpAlertDialog(context)
         connecter = Connecter(popUpAlertDialog,context)

        popUpAlertDialog.init()
        connecter.init()
    }

}