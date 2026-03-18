package com.example.pkm_forms_proyecto1.backend.elementos

import androidx.compose.ui.graphics.Color
import com.example.pkm_forms_proyecto1.enums.TipoBorde

class Borde(val tipoBorde: TipoBorde, val grosor: Double) {
    var color: Color? = null
    var colorCadena: String = ""
}