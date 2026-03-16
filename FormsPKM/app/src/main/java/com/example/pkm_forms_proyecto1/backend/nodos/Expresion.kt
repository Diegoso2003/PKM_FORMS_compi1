package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.enums.Tipo

class Expresion(val objeto: Any, val tipo: Tipo) :
    NodoExpresion() {
    var linea: Int = 0
    var columna: Int = 0
    var nombreID: String = ""

    constructor(objeto: Any, tipo: Tipo, linea: Int, columna: Int):this(objeto, tipo){
        this.linea = linea
        this.columna = columna
    }

    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        TODO("Not yet implemented")
    }
}