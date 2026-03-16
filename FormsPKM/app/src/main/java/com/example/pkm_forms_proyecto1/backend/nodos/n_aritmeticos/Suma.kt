package com.example.pkm_forms_proyecto1.backend.nodos.n_aritmeticos

import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoAritmetico
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class Suma(
    nodoIzq: NodoExpresion, nodoDer: NodoExpresion
) : NodoAritmetico(nodoIzq, nodoDer) {
    override fun realizarOperacion(
        expr1: Expresion, expr2: Expresion
    ): Expresion {
        TODO("Not yet implemented")
    }
}