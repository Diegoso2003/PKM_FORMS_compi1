package com.example.pkm_forms_proyecto1.backend.condiciones

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class MayorIgual(simbolo: Simbolo, expr1: NodoExpresion, expr2: NodoExpresion) :
    CondicionNormal(simbolo, expr1, expr2) {
    override fun evaluarExpresiones(
        expr1: Expresion, expr2: Expresion
    ): Boolean {
        return expr1.objeto as Double >= expr2.objeto as Double
    }
}