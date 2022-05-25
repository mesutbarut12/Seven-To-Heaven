package com.barut.unterkontenverwaltung.action

class EinigeChecksFürBessereSicherheit {




    fun checkUserPutKommaOrPunkt(userInput : String) : String{

            val newValue = userInput.replace(",",".")
            return newValue


    }
}