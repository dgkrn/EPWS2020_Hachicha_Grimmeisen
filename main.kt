import java.util.*

fun main () {



    var ort1 = Ort("Deutschland", "NRW", "Gummersbach", 51643)
    var ort2 = Ort("Deutschland", "BW", "Pforzheim", 75337)
    var ort3 = Ort("Deutschland", "NRW", "Düsseldorf", 40210)

    var listOrteMona  = mutableListOf<Ort>(ort1,ort2,ort3)

    var impfung1 = Impfung("BioNTech", true,1 )

    var mona = Person("Mona","Lerch", Adresse(51643,
            "adp",4), listOrteMona, impfung1)

    //println(mona.risikoIndex)
    mona.datenAusgeben()


}
