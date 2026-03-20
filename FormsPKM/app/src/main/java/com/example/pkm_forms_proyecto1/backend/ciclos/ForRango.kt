package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo

class ForRango(simbolo: Simbolo, val inicio: Int, val ultimo: Int) : TipoFor(simbolo) {

    override fun condicion(infoCalculo: InfoCalculo): Boolean {
        val estado = simboloTabla.valor as Int
        return estado in inicio..ultimo
    }

    override fun instruccionSiguiente(infoCalculo: InfoCalculo) {
        simboloTabla.valor = (simboloTabla.valor as Int) + 1
    }

    override fun calcularInicio(infoCalculo: InfoCalculo): Double {
        return inicio.toDouble()
    }
}