package com.barut.unterkontenverwaltung.bottomnavigation.additem.connecter

import android.content.Context
import android.view.View
import com.barut.unterkontenverwaltung.DataTransferPopUp
import com.barut.unterkontenverwaltung.bottomnavigation.additem.popup.PopUpAlertDialog
import com.barut.unterkontenverwaltung.bottomnavigation.additem.setitems.SetItemAusgaben
import com.barut.unterkontenverwaltung.bottomnavigation.additem.setitems.SetItemEinkommen
import com.barut.unterkontenverwaltung.bottomnavigation.additem.setitems.SetItemUnterkonto
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain

class Connecter(private val popUpAlertDialog: PopUpAlertDialog, private val context: Context) {

    fun init() {
        popUpAlertDialog.onClickListener(object : DataTransferPopUp {
            override fun getData(klick: Int, view: View, alertdialog: AlertDialogMain) {
                if (klick == 1) {
                    SetItemEinkommen(view, context).init()
                } else if (klick == 2) {
                    SetItemUnterkonto(view, context).init()
                } else if (klick == 3) {
                    SetItemAusgaben(view, context).init()
                }
            }
        })
    }
}