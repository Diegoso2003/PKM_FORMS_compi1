package com.example.pkm_forms_proyecto1.backend.color

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo

abstract class TipoColor(val simbolo: Simbolo) {
    abstract fun cadena(infoCalculo: InfoCalculo): String

    protected fun validarNumber(infoCalculo: InfoCalculo, nodoExpresion: NodoExpresion): Double{
        val expr = nodoExpresion.evaluarNodo(infoCalculo)
        when (expr.tipo){
            Tipo.NUMBER -> return expr.objeto as Double
            Tipo.ERROR_SEMANTICO -> return 0.0
            Tipo.STRING, Tipo.SPECIAL -> {
                agregarMensaje(infoCalculo, simbolo, "este atributo solo acepta variables tipo string")
                return 0.0
            }
        }
    }
}