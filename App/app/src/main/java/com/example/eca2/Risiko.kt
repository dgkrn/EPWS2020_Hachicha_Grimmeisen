package com.example.eca2


fun main () {

    val rIndex = Risiko().berechneRisiko(4000,4000,true)
    println(rIndex)
}


class Risiko () {

    fun berechneRisiko(anzahlKontakte: Int, anzahlMitarbeiter : Int, personArbeitet : Boolean) : Float {

        var risikoIndex  = 1.0f
        var kontaktPersonenAnzahl = anzahlKontakte(anzahlMitarbeiter, anzahlKontakte)

        if (personArbeitet){
            risikoIndex = risikoArbeit(risikoIndex, anzahlMitarbeiter)
        }
        // risikoAusgabeNutzer()

        risikoIndex += kontaktRisiko(kontaktPersonenAnzahl)
        if (risikoIndex > 10) risikoIndex = 10.0f
        return risikoIndex
    }

    fun anzahlKontakte(anzahlMitarbeiter : Int, anzahlMitbewohner : Int) : Float{
        var anzahlKontakte = 0.0F
        anzahlKontakte = anzahlMitbewohner.toFloat()
        // Anzahl der Freunde miteinberechnen

        if(anzahlMitarbeiter < 20) {
            for (i in 0..anzahlMitarbeiter) {
                anzahlKontakte += 0.15f
            }
        } else anzahlKontakte += 3.5f

        if (anzahlKontakte > 10.0f) anzahlKontakte = 10.0f
        return anzahlKontakte
    }

    fun kontaktRisiko (anzahlKontakte: Float) : Float {
        var risikoIndex = 1.0f
        // 8.5 = hoch
        // 10 = Sehr hoch
        // 5 = mittel
        if (anzahlKontakte >= 8.5f)  risikoIndex = 2.5f
        if (anzahlKontakte in 5.5f..10f) risikoIndex = 2.3f // Hier
        if (anzahlKontakte in 3f..5.4f) risikoIndex = 2f
        if (anzahlKontakte in 2f..3f) risikoIndex = 1f
        if (anzahlKontakte < 2f) risikoIndex = 0f

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

        // +0.5 da die Personen regelmäßig Arbeiten geht
        risikoIndex+0.5f

        if (anzahlMitarbeiter < 100) {
            risikoIndex + 0.2
        }

        if (anzahlMitarbeiter in 100..150) {
            risikoIndex + 0.4
        }

        if (anzahlMitarbeiter in 150..400){
            risikoIndex+0.8
        }
        if (anzahlMitarbeiter > 400 ) {
            risikoIndex+1
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


}
