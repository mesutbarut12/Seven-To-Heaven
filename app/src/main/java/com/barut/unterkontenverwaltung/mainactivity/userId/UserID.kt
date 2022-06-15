package com.barut.unterkontenverwaltung.mainactivity.userId

import android.content.Context
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.UserIdModel
import java.util.*

class UserID(private val context: Context) {

    val sQliteInit = SQliteInit(context)
    val uuid = UUID.randomUUID()

    fun init() {
            if(sQliteInit.userId().readData().isEmpty()) {
                val model = UserIdModel(uuid.toString())
                sQliteInit.userId().setData(model)
            }

    }
    fun getUserId() : String {
        var id = ""
        if(sQliteInit.userId().readData().isNotEmpty()) {
            for (i in sQliteInit.userId().readData()) {
                id = i.id
            }
        }
        return id
    }

}