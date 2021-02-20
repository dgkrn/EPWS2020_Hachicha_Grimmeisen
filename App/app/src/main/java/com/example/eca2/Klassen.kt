package com.example.eca2

import java.util.*


class TestErgebnis (){
    var ergebnisNegativ : Boolean? = null
    var standort : String? = null
    var datum : Date? = null
    var person : Person? = null
}

open class Person (var vorName : String, var nachName : String,var adresse: Adresse,
                   var orte : List<Ort>, var impfung : Impfung) {

    var handyNummer : Int? = null
    // var risikoIndex = Risiko().berechneRisiko



    fun datenAusgeben () {
        println("Name: $vorName\nNachname: $nachName\nAdresse: ${adresse.strasse} Nr: ${adresse.hausnummer}\n${adresse.plz} ${orte[0].stadt}")
    }
}

class Kontakte (list: List<Person>){

    fun letzteKontakte (mutableList: AbstractMutableList<Kontakte>) : List<Kontakte> {
        var listLetzteKontakte = listOf<Kontakte>()

        TODO()
    }
}

class Ort (var land : String, var bundesLand : String, var stadt : String, var plz : Int){
    var zeitPunkt = Uhrzeit(13,10)
}

class Uhrzeit (val stunde : Int, val minute : Int)

class Adresse (var plz : Int, var strasse : String, var hausnummer : Int)

class Map (testZentrun : TestZentrum, var risikoGebiet : Ort, var infektionsGeschehen : Int)

class Impfung (val art : String, var verfügbar : Boolean, var nteImpfung : Int)

class TestZentrum (val name : String, val ort: Ort)
