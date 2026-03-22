package com.example.pkm_forms_proyecto1.backend.color

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo

class ColorCadena(simbolo: Simbolo, val caden: String): TipoColor(simbolo) {
    override fun cadena(infoCalculo: InfoCalculo): String {
        return caden
    }
}