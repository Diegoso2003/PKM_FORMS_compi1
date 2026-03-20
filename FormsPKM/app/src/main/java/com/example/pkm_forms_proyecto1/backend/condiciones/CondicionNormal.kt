package com.example.pkm_forms_proyecto1.backend.condiciones

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo

abstract class CondicionNormal(simbolo: Simbolo, val expr1: NodoExpresion, val expr2: NodoExpresion): Condicion(simbolo) {

    override fun evaluarCondicion(infoCalculo: InfoCalculo): Boolean {
        val exprAux1 = expr1.evaluarNodo(infoCalculo)
        val exprAux2 = expr2.evaluarNodo(infoCalculo)
        if (exprAux1.tipo == Tipo.NUMBER && exprAux2.tipo == Tipo.NUMBER) {
            return evaluarExpresiones(exprAux1, exprAux2)
        }
        if (exprAux1.tipo == Tipo.ERROR_SEMANTICO || exprAux2.tipo == Tipo.ERROR_SEMANTICO) return true
        val expr = if (exprAux1.tipo == Tipo.STRING) exprAux1 else exprAux2
        agregarMensaje(
            infoCalculo,
            expr.simbolo,
            "las condiciones solo son validas con variables tipo number"
        )
        return true
    }

    abstract fun evaluarExpresiones(expr1: Expresion, expr2: Expresion): Boolean
}