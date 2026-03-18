package com.example.pkm_forms_proyecto1.backend.elementos

import androidx.compose.ui.graphics.Color
import com.example.pkm_forms_proyecto1.enums.TLetra

class Estilo: Elemento("STYLE") {
    var color: Color?= null
    var colorCadena: String? = null
    var textSize: Double? = null
    var border: Borde? = null
    var fontFamily: TLetra? = null
}