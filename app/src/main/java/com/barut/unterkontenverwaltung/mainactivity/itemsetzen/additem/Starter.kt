package com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem

import android.content.Context
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.connecter.Connecter
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.PopUpAlertDialog

class Starter(val context : Context,private val dataTransferUserAddedItem: DataTransferUserAddedItem){
    private lateinit var popUpAlertDialog: PopUpAlertDialog
    private lateinit var connecter: Connecter

    fun init(){
         popUpAlertDialog = PopUpAlertDialog(context)
         connecter = Connecter(popUpAlertDialog,context,dataTransferUserAddedItem)

        popUpAlertDialog.init()
        connecter.init()
    }

}