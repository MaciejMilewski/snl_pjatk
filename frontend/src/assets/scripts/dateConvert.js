
export default function getDatePolish(data) {
    //Dzień
    let nazwaDnia = new Array(7)
    nazwaDnia[0] = "Niedziela"
    nazwaDnia[1] = "Poniedziałek"
    nazwaDnia[2] = "Wtorek"
    nazwaDnia[3] = "Środa"
    nazwaDnia[4] = "Czwartek"
    nazwaDnia[5] = "Piątek"
    nazwaDnia[6] = "sobota"

    //Miesiąc
    let nazwaMiesiaca = new Array(12)
    nazwaMiesiaca[0] = "stycznia "
    nazwaMiesiaca[1] = "lutego "
    nazwaMiesiaca[2] = "marca "
    nazwaMiesiaca[3] = "kwietnia "
    nazwaMiesiaca[4] = "maja "
    nazwaMiesiaca[5] = "czerwca "
    nazwaMiesiaca[6] = "lipca "
    nazwaMiesiaca[7] = "sierpnia "
    nazwaMiesiaca[8] = "września "
    nazwaMiesiaca[9] = "października "
    nazwaMiesiaca[10] = "listopada "
    nazwaMiesiaca[11] = "grudnia "
    
    var weekend = data.getDay()
    var miesiac = data.getMonth()
    var dzien = data.getDate()
    var rok = data.getFullYear()
    if(rok <= 99)
        rok += 1900
    return nazwaDnia[weekend] + "," + " " + dzien + " " + nazwaMiesiaca[miesiac] + "" + rok
}