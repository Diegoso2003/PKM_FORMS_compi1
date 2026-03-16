package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import java.util.LinkedList

class InfoCalculo {
    val tablaSimbolos: TablaSimbolos?
    val listaComodin: LinkedList<Expresion>?
    val listaErrores: LinkedList<MensajeError>
    var linea: Int = 0
    var columna: Int = 0

    constructor(tablaSimbolos: TablaSimbolos, listaErrores: LinkedList<MensajeError>){
        this.tablaSimbolos = tablaSimbolos
        this.listaErrores = listaErrores
        listaComodin = null
    }

    constructor(listaComodin: LinkedList<Expresion>, listaErrores: LinkedList<MensajeError>, linea: Int, columna: Int){
        this.listaComodin = listaComodin
        this.listaErrores = listaErrores
        tablaSimbolos = null
        this.linea = linea
        this.columna = columna
    }

    fun aceptaIds(): Boolean{
        return tablaSimbolos != null
    }

    fun aceptaComodines(): Boolean{
        return listaComodin != null
    }
}