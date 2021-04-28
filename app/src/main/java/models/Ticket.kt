package models

import androidx.navigation.fragment.FragmentNavigator
import java.security.CodeSource

class Ticket {
    var name: String=""
    var source: String=""
    var destination:String=""
    var tnumber:String=""


    constructor(name: String, source: String, tnumber:String, destination: String){
        this.name = name
        this.source = source
        this.destination = destination
        this.tnumber= tnumber
    }


}