package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.SimboloTabla
import com.example.pkm_forms_proyecto1.enums.Tipo

abstract class TipoFor(val simbolo: Simbolo) {
    protected lateinit var simboloTabla: SimboloTabla
    fun inicio(infoCalculo: InfoCalculo){
        var comienzo = calcularInicio(infoCalculo)
        if (infoCalculo.formulario.tabla.existeSimbolo(simbolo.lexema)) {
            simboloTabla = infoCalculo.formulario.tabla.obtenerSimbolo(simbolo.lexema)
            if (simboloTabla.tipo != Tipo.NUMBER) {
                agregarMensaje(
                    infoCalculo,
                    simbolo,
                    "variable ya declarada como tipo ${simboloTabla.tipo.name.lowercase()}"
                )
                return
            }
            simboloTabla.valor = comienzo
            return
        }
        simboloTabla =
            SimboloTabla(simbolo.lexema, Tipo.NUMBER, simbolo.linea, simbolo.columna, comienzo)
        infoCalculo.formulario.tabla.agregarALaTabla(simbolo.lexema, simboloTabla)
    }
    abstract fun condicion(infoCalculo: InfoCalculo):Boolean
    abstract fun instruccionSiguiente(infoCalculo: InfoCalculo)
    abstract fun calcularInicio(infoCalculo: InfoCalculo): Double
}