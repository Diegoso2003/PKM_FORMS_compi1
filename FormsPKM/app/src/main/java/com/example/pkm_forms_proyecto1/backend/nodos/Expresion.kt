package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.enums.Tipo

class Expresion(val objeto: Any, val tipo: Tipo, simbolo: Simbolo) : NodoExpresion(simbolo) {
    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        return this
    }

    constructor (objeto: Any, tipo: Tipo) : this(objeto, tipo, Simbolo(0, 0, ""))
}