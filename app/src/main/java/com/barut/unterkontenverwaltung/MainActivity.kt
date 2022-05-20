package com.barut.unterkontenverwaltung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val test = AlertDialogMain(this,R.layout.activity_main)
        test.createDialog()


    }
}