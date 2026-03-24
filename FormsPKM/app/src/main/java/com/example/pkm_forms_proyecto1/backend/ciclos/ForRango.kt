package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo

class ForRango(simbolo: Simbolo, val inicio: Int, val ultimo: Int) : TipoFor(simbolo) {

    override fun condicion(infoCalculo: InfoCalculo): Boolean {
        var estado = simboloTabla.valor
        if(estado !is Int){
            estado = (estado as Double).toInt()
        }
        return estado in inicio..ultimo
    }

    override fun instruccionSiguiente(infoCalculo: InfoCalculo) {
        simboloTabla.valor = (simboloTabla.valor as Double) + 1.0
    }

    override fun calcularInicio(infoCalculo: InfoCalculo): Double {
        return inicio.toDouble()
    }
}