package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo

abstract class NodoAritmetico(
    nodoIzq: NodoExpresion, nodoDer: NodoExpresion
) : NodoExpresion() {
    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        TODO("Not yet implemented")
    }

    abstract fun realizarOperacion(expr1: Expresion, expr2: Expresion): Expresion
}