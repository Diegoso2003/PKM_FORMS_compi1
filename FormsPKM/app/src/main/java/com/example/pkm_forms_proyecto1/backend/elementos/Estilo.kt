package com.example.pkm_forms_proyecto1.backend.elementos

import androidx.compose.ui.graphics.Color
import com.example.pkm_forms_proyecto1.backend.Contador
import com.example.pkm_forms_proyecto1.backend.color.TipoColor
import com.example.pkm_forms_proyecto1.enums.ColorToken
import com.example.pkm_forms_proyecto1.enums.TLetra

class Estilo: Elemento("STYLE") {
    var color: Color?= null
    var colorCadena: String = ""
    var colorBack: Color? = null
    var colorBackCadena: String = ""
    var textSize: Double = -1.0
    var border: Borde? = null
    var fontFamily: TLetra? = null
    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<style>\n")
        cadena.append("\t<color=")
        if(colorCadena.isBlank()) cadena.append("#000000") else cadena.append(colorCadena)
        cadena.append("/>\n\t<background color=")
        if(colorBackCadena.isBlank()) cadena.append("#FFFFFF") else cadena.append(colorBackCadena)
        cadena.append("/>\n\t<font family=")
        if(fontFamily == null) cadena.append("MONO") else cadena.append(fontFamily!!.name)
        cadena.append("/>\n\t<text size=")
        if(textSize < 0.0) cadena.append("10") else cadena.append(DAC(textSize))
        cadena.append("/>\n")
        if(border != null) cadena.append("\t${border!!.aCadena()}\n")
        cadena.append("</style>")
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return true
    }

    override fun aumentarContador(contador: Contador) {

    }
}