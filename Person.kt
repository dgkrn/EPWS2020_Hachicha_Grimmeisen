
open class Person (var vorName : String, var nachName : String,var adresse: Adresse,
                   var orte : List<Ort>, var impfung : Impfung) {

    var handyNummer : Int? = null
    var risikoIndex = Risiko().berechneRisiko()



fun datenAusgeben () {
    println("Name: $vorName\nNachname: $nachName\nAdresse: ${adresse.strasse} Nr: ${adresse.hausnummer}\n${adresse.plz} ${orte[0].stadt}")
}



}

