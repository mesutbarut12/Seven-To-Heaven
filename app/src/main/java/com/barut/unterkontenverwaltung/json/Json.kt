package com.barut.unterkontenverwaltung.json

import android.content.Context
import android.widget.Toast
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class Json(val userId: String) {


    fun save(inhalt: HashMap<String,ArrayList<Model>>) {
        val db = Firebase.firestore
        db.collection("Speicher").document(userId).
        set(hashMapOf(userId to inhalt)).addOnSuccessListener {

        }

    }
}
