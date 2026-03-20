package com.example.pkm_forms_proyecto1.backend.condiciones

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo

class Negacion(simbolo: Simbolo, val condicion: Condicion): Condicion(simbolo) {
    override fun evaluarCondicion(infoCalculo: InfoCalculo): Boolean {
        return !condicion.evaluarCondicion(infoCalculo)
    }
}