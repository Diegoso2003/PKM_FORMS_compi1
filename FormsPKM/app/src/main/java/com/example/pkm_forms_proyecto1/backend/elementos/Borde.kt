package com.example.pkm_forms_proyecto1.backend.elementos

import androidx.compose.ui.graphics.Color
import com.example.pkm_forms_proyecto1.enums.TipoBorde
import java.util.Locale

class Borde(val tipoBorde: TipoBorde, val grosor: Double) {
    var color: Color? = null
    var colorCadena: String = ""
    fun aCadena(): String{
        return "<border, ${String.format(Locale.US, "%.2f", grosor)}, ${tipoBorde.name}, color=${colorCadena}/>"
    }
}