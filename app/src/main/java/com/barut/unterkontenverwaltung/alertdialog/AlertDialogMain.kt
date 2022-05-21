package com.barut.unterkontenverwaltung.alertdialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View

class AlertDialogMain(val context: Context, val layout : Int) {

    private lateinit var build : AlertDialog.Builder
    private lateinit var created : AlertDialog

     fun setLayout() : View {
        val view = LayoutInflater.from(context).inflate(layout,null)
        build = AlertDialog.Builder(context)
        build.setView(view)
        return view
    }
    fun createDialog(){

        created = build.create()
        created.show()
    }
    fun cancelDialog(){
        created.cancel()
    }


}