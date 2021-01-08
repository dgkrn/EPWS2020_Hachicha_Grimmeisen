import java.util.*

class Kontakte (list: List<Person>){

    fun letzteKontakte (mutableList: AbstractMutableList<Kontakte>) : List<Kontakte> {
        var listLetzteKontakte = listOf<Kontakte>()

        TODO()
    }
}

class Uhrzeit (val stunde : Int, val minute : Int)

class Adresse (var plz : Int, var strasse : String, var hausnummer : Int)

class Ort (var land : String, var bundesLand : String, var stadt : String, var plz : Int){
    var zeitPunkt = Uhrzeit(13,10)
}

class Map (testZentrun : TestZentrum, var risikoGebiet : Ort, var infektionsGeschehen : Int)

class Impfung (val art : String, var verfügbar : Boolean, var nteImpfung : Int)

class TestZentrum (val name : String, val ort: Ort)

class TestErgebnis (){
    var ergebnisNegativ : Boolean? = null
    var standort : String? = null
    var datum : Date? = null
    var person : Person? = null
}

class Bewegungsdaten () {
    lateinit var ort : Ort
    lateinit var besuchterFreund : Person
    lateinit var zeitPunkt : Uhrzeit
    var entfernung : Int = 0
}

