package com.barut.unterkontenverwaltung

class EinigeChecksFürBessereSicherheit {




    fun checkUserPutKommaOrPunkt(userInput : String) : String{

            val newValue = userInput.replace(",",".")
            return newValue


    }
}