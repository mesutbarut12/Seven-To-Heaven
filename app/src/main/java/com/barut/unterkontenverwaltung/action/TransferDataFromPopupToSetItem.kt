package com.barut.unterkontenverwaltung.action

import android.view.View
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain

interface TransferDataFromPopupToSetItem {

    fun getClick(klick : Int, view : View,alertdialog : AlertDialogMain)
}