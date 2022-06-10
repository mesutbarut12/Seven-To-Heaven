package com.barut.unterkontenverwaltung

import android.view.View
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain

interface Interfaces {
}


interface DataTransferPopUp {
    fun getData(klick : Int, view : View, alertdialog : AlertDialogMain)
}
interface DataTransferBottomNavigation {
    fun getData(klick : Int)
}
interface DataTransferShowUnterkontoBinder{
    fun getData(name : String)
}