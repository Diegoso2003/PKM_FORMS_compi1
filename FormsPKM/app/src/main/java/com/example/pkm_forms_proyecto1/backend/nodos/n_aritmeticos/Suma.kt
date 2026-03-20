package com.example.pkm_forms_proyecto1.backend.nodos.n_aritmeticos

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoAritmetico
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo
import java.util.Locale

class Suma(
    nodoIzq: NodoExpresion, nodoDer: NodoExpresion, simbolo: Simbolo
) : NodoAritmetico(simbolo, nodoIzq, nodoDer, "suma") {
    override fun realizarOperacion(
        expr1: Expresion, expr2: Expresion
    ): Expresion {
        if (expr1.tipo == Tipo.NUMBER && expr2.tipo == Tipo.NUMBER) {
            val valor1 = expr1.objeto as Double
            val valor2 = expr2.objeto as Double
            return Expresion((valor1 + valor2), Tipo.NUMBER, expr1.simbolo)
        }
        val valor1 = if (expr1.tipo == Tipo.NUMBER) String.format(
            Locale.US,
            "%.2f",
            expr1.objeto
        ) else expr1.objeto.toString()
        val valor2 = if (expr1.tipo == Tipo.NUMBER) String.format(
            Locale.US,
            "%.2f",
            expr2.objeto
        ) else expr2.objeto.toString()
        return Expresion((valor1 + valor2), Tipo.STRING, expr1.simbolo)
    }
}