package com.example.pkm_forms_proyecto1.backend.nodos.n_aritmeticos

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoAritmetico
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.excepciones.ErrorSemantico

class Division(
    nodoIzq: NodoExpresion, nodoDer: NodoExpresion, simbolo: Simbolo
) : NodoAritmetico(simbolo,nodoIzq, nodoDer, "División") {
    override fun realizarOperacion(
        expr1: Expresion, expr2: Expresion
    ): Expresion {
        if (expr1.tipo == Tipo.NUMBER && expr2.tipo == Tipo.NUMBER) {
            val valor1 = expr1.objeto as Double
            val valor2 = expr2.objeto as Double
            if (valor2 == 0.0) {
                throw ErrorSemantico(
                    mensajeDeError(
                        expr2,
                        "La división entre cero no es permitida."
                    )
                )
            }
            return Expresion((valor1 / valor2), Tipo.NUMBER, expr1.simbolo)
        }
        val exprAux = if (expr1.tipo != Tipo.NUMBER) expr1 else expr2
        throw ErrorSemantico(mensajeDeError(exprAux))
    }
}