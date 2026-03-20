package com.example.pkm_forms_proyecto1.backend.condiciones

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo

class CondicionExpresion(simbolo: Simbolo, val expr1: NodoExpresion) : Condicion(simbolo) {
    override fun evaluarCondicion(infoCalculo: InfoCalculo): Boolean {
        val expr = expr1.evaluarNodo(infoCalculo)
        if (expr.tipo == Tipo.NUMBER) {
            return expr.objeto as Double >= 1.0
        }
        if (expr.tipo == Tipo.ERROR_SEMANTICO) return false
        agregarMensaje(
            infoCalculo,
            expr.simbolo,
            "las condiciones solo son validas con variables tipo number"
        )
        return false
    }
}