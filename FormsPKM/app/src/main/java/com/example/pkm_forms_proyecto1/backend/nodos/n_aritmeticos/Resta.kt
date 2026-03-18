package com.example.pkm_forms_proyecto1.backend.nodos.n_aritmeticos

import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoAritmetico
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.excepciones.ErrorSemantico

class Resta(
    nodoIzq: NodoExpresion, nodoDer: NodoExpresion
) : NodoAritmetico(nodoIzq, nodoDer, "resta") {
    override fun realizarOperacion(
        expr1: Expresion, expr2: Expresion
    ): Expresion {
        if(expr1.tipo == Tipo.NUMBER && expr2.tipo == Tipo.NUMBER){
            val valor1 = expr1.objeto as Double
            val valor2 = expr2.objeto as Double
            return Expresion((valor1 - valor2), Tipo.NUMBER, expr1.linea, expr1.columna)
        }
        val exprAux = if (expr1.tipo != Tipo.NUMBER) expr1 else expr2
        throw ErrorSemantico(mensajeDeError(exprAux))
    }
}