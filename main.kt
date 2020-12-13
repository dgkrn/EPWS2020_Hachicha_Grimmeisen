import java.util.*


class Adresse (var plz : Int, var strasse : String, var hausnummer : Int)

class Person (var name : String, var vorName : String, var adresse: Adresse, var handyNummer : Int )

class TestErgebnis (var ergebis : Boolean, var standort : String, var datum : Date, var person : Person)

class RisikoStatus  {
    
    var risikoIndex  = 1.0
    var kontakteAnzahl : Int  = 0

}
