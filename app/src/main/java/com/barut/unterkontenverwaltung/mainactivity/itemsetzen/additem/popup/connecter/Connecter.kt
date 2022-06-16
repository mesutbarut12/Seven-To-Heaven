package com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.connecter

import android.content.Context
import android.view.View
import com.barut.unterkontenverwaltung.DataTransferPopUp
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.PopUpAlertDialog
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.connecter.setitems.SetItemAusgaben
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.connecter.setitems.SetItemEinkommen
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.popup.connecter.setitems.SetItemUnterkonto
import com.barut.unterkontenverwaltung.allgemein.alertdialog.AlertDialogMain

class Connecter(private val popUpAlertDialog: PopUpAlertDialog, private val context: Context,
                private val dataTransferUserAddedItem: DataTransferUserAddedItem) {

    fun init() {
        popUpAlertDialog.onClickListener(object : DataTransferPopUp {
            override fun getData(klick: Int, view: View, alertdialog: AlertDialogMain) {
                if (klick == 1) {
                    SetItemEinkommen(view, context,alertdialog,dataTransferUserAddedItem).init()
                } else if (klick == 2) {
                    SetItemUnterkonto(view, context,alertdialog,dataTransferUserAddedItem).init()
                } else if (klick == 3) {
                    SetItemAusgaben(view, context,alertdialog,dataTransferUserAddedItem).init()
                }
            }
        })
    }
}