package com.barut.unterkontenverwaltung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SQLiteMain(this,"Test","test","test1","test2",
        "id").setData(SQLiteModel("hallo","wiegehts"))




    }
}