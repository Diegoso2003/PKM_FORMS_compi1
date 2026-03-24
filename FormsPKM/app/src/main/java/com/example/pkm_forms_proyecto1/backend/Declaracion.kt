package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.backend.nodos.elementos.ElementoSimple
import com.example.pkm_forms_proyecto1.enums.Tipo

class Declaracion(simbolo: Simbolo, val objeto: Any?, val identi: String, val tipo: Tipo) :
    Accion(simbolo) {

    override fun realizarAccion(infoCalculo: InfoCalculo) {
        if (infoCalculo.formulario.tabla.existeSimbolo(identi)) {
            agregarMensaje(infoCalculo, simbolo, "ya existe una variable con este nombre")
            return
        }
        if (tipo == Tipo.SPECIAL) {
            evaluarEspecial(infoCalculo)
            return
        }
        if(tipo == Tipo.NUMBER){
            evaluarNumber(infoCalculo)
            return
        }
        if(tipo == Tipo.STRING){
            evaluarString(infoCalculo)
        }
    }

    private fun evaluarString(infoCalculo: InfoCalculo){
        if(objeto == null){
            agregarAlaTabla(infoCalculo, "")
            return
        }
        if(objeto !is NodoExpresion){
            agregarAlaTabla(infoCalculo, "")
            agregarMensaje(infoCalculo, simbolo, "variable string solo acepta cadenas")
            return
        }
        val expr = objeto.evaluarNodo(infoCalculo)
        if(expr.tipo == Tipo.STRING){
            agregarAlaTabla(infoCalculo, expr.objeto)
            return
        }
        agregarAlaTabla(infoCalculo, "")
        if(expr.tipo == Tipo.ERROR_SEMANTICO){
            return
        }
        agregarMensaje(infoCalculo, simbolo, "variable tipo string solo almacena números")
    }

    private fun evaluarNumber(infoCalculo: InfoCalculo){
        if(objeto == null){
            agregarAlaTabla(infoCalculo, 0.0)
            return
        }
        if(objeto !is NodoExpresion){
            agregarAlaTabla(infoCalculo, 0.0)
            agregarMensaje(infoCalculo, simbolo, "variable tipo number solo almacena números")
            return
        }
        val expr = objeto.evaluarNodo(infoCalculo)
        if(expr.tipo == Tipo.NUMBER){
            agregarAlaTabla(infoCalculo, expr.objeto)
            return
        }
        agregarAlaTabla(infoCalculo, 0.0)
        if(expr.tipo == Tipo.ERROR_SEMANTICO){
            return
        }
        agregarMensaje(infoCalculo, simbolo, "variable tipo number solo almacena números")
    }

    private fun evaluarEspecial(infoCalculo: InfoCalculo){
        if (objeto == null) {
            agregarMensaje(infoCalculo, simbolo, "variable special debe inicializarse")
            return
        }
        if (objeto is ElementoSimple) {
            agregarAlaTabla(infoCalculo, objeto)
            return
        }
        agregarMensaje(infoCalculo, simbolo, "variable special solo puede almacenar preguntas")
    }

    fun agregarAlaTabla(infoCalculo: InfoCalculo, objeto: Any){
        infoCalculo.formulario.tabla.agregarALaTabla(
            identi,
            SimboloTabla(identi, tipo, simbolo.linea, simbolo.columna, objeto)
        )
    }

}