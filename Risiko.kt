import kotlin.math.roundToLong

class Risiko () {

    // Einschätzung des Risikos (1 = kein Risiko; 10 = höchstes Risiko)
    var risikoIndex  = 1.0f
        set(value : Float) {
            if (value !in 0.0f..10.0f)
                field = 0.0f
            else
                field = value
        }


    var personArbeitet : Boolean = false
    var anzahlKontakte = 4


    fun berechneRisiko() : Float {

        risikoKontakte()
        risikoAusgabeNutzer()
        if (personArbeitet) risikoArbeit()

        return risikoIndex
    }

    fun risikoKontakte()  {
        for (i in 0..anzahlKontakte) risikoIndex += 0.3f
    }

    fun risikoAusgabeNutzer () {
        print("Möchten Sie wissen wie diese Einschätzung berechnet wurde?\n")
        val bEingabe = readLine()?.toUpperCase() ?:"NEIN"
        if (bEingabe == "JA"){
            println ("Der Risiko-Index wurde wie folgt berechnet:")
            TODO("Transparente Ausgabe der verwendeten Algorithmen")
        }
    }

    fun risikoArbeit () {
        var anzahlMitarbeiter = 300
        if (personArbeitet){
            risikoIndex+0.5f
            if (anzahlMitarbeiter >= 150 && anzahlMitarbeiter <= 400){
                risikoIndex+0.5
            }
            if (anzahlMitarbeiter > 400 ) {
                risikoIndex++
            }
        }
    }

    fun coronaAmpel () : String {
        when(risikoIndex){
            in 0.0..3.0 -> return "Grün"
            in 3.1..6.0 -> return "Gelb"
            in 6.1..10.0 -> return "Rot"
        }
        return "Fehler"
    }

    fun testErgebnis (test : TestErgebnis) : Float {
        if (TestErgebnis().ergebnisNegativ  == true) {
            risikoIndex = 1.0f
        } else if (TestErgebnis().ergebnisNegativ == false ) risikoIndex = 10.0f
        else return risikoIndex

        return risikoIndex
    }






}






// 2


package com.example.eca2

data class RiskData (var username : String, var userWorks: Boolean, var userWorkCount : Int)

class Risiko () {

    // Einschätzung des Risikos (1 = kein Risiko; 10 = höchstes Risiko)
    /*

    var risikoIndex  = 1.0f
        set(value : Float) {
            if (value !in 0.0f..10.0f)
                field = 0.0f
            else
                field = value
        }


     */



    // var personArbeitet : Boolean = false
    // var anzahlKontakte = 0


    fun berechneRisiko(anzahlKontakte: Int, personArbeitet : Boolean, anzahlMitarbeiter : Int) : Float {

        var risikoIndex : Float = 0.0F

        if (anzahlKontakte!=0){
            risikoIndex = risikoKontakte(anzahlKontakte)
        }

        if (personArbeitet){
            risikoIndex = risikoArbeit(anzahlMitarbeiter)
        }
        risikoAusgabeNutzer()

        return risikoIndex
    }

    fun risikoKontakte(anzahlKontakte: Int) : Float {
        var risikoIndex = 0.0F
        for (i in 0..anzahlKontakte) risikoIndex += 0.3f
        return risikoIndex
    }

    fun risikoAusgabeNutzer () {
        print("Möchten Sie wissen wie diese Einschätzung berechnet wurde?\n")
        val bEingabe = readLine()?.toUpperCase() ?:"NEIN"
        if (bEingabe == "JA"){
            println ("Der Risiko-Index wurde wie folgt berechnet:")
            TODO("Transparente Ausgabe der verwendeten Algorithmen")
        }
    }

    fun risikoArbeit (risikoIndex : Float, anzahlMitarbeiter : Int) : Float {

        risikoIndex+0.5f

        if (anzahlMitarbeiter >= 150 && anzahlMitarbeiter <= 400){
            risikoIndex+0.5
        }
        if (anzahlMitarbeiter > 400 ) {
            risikoIndex++
        }
        return risikoIndex
    }



    fun coronaAmpel (risikoIndex: Float) : String {
        when(risikoIndex){
            in 0.0..3.0 -> return "Grün"
            in 3.1..6.0 -> return "Gelb"
            in 6.1..10.0 -> return "Rot"
        }
        return "Fehler"
    }

    fun testErgebnis (risikoIndex: Float, test : TestErgebnis) : Float {
        if (TestErgebnis().ergebnisNegativ  == true) {
            risikoIndex = 1.0f
        } else if (TestErgebnis().ergebnisNegativ == false ) risikoIndex = 10.0f
        else return risikoIndex

        return risikoIndex
    }
}
