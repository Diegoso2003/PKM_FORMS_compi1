package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.backend.nodos.elementos.ElementoSimple
import com.example.pkm_forms_proyecto1.enums.Tipo

class Asignacion(simbolo: Simbolo, val objeto: Any, val identi: String) : Accion(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        if (!infoCalculo.formulario.tabla.existeSimbolo(identi)) {
            agregarMensaje(infoCalculo, simbolo, "variable no declarada")
            return
        }
        val simboloTabla = infoCalculo.formulario.tabla.obtenerSimbolo(identi)
        if (simboloTabla.tipo == Tipo.SPECIAL) {
            validarEspecial(infoCalculo, simboloTabla)
            return
        }
        if (simboloTabla.tipo == Tipo.NUMBER) {
            validarNumber(infoCalculo, simboloTabla)
            return
        }
        if (simboloTabla.tipo == Tipo.STRING) {
            validarString(infoCalculo, simboloTabla)
        }
    }

    private fun validarString(
        infoCalculo: InfoCalculo, simboloTabla: SimboloTabla
    ) {
        if (objeto !is NodoExpresion) {
            agregarMensaje(
                infoCalculo,
                simbolo,
                "variable string solo permite almacenar cadenas de texto."
            )
            return
        }
        val expr = objeto.evaluarNodo(infoCalculo)
        if (expr.tipo == Tipo.STRING) {
            simboloTabla.valor = expr.objeto
            return
        }
        if (expr.tipo != Tipo.ERROR_SEMANTICO) {
            agregarMensaje(
                infoCalculo,
                simbolo,
                "variable string solo permite almacenar cadenas de texto"
            )
        }
    }

    private fun validarNumber(infoCalculo: InfoCalculo, simboloTabla: SimboloTabla) {
        if (objeto !is NodoExpresion) {
            agregarMensaje(infoCalculo, simbolo, "variable number solo permite almacenar números")
            return
        }
        val expr = objeto.evaluarNodo(infoCalculo)
        if (expr.tipo == Tipo.NUMBER) {
            simboloTabla.valor = expr.objeto
            return
        }
        if (expr.tipo != Tipo.ERROR_SEMANTICO) {
            agregarMensaje(infoCalculo, simbolo, "variable number solo permite almacenar números")
        }
    }

    private fun validarEspecial(infoCalculo: InfoCalculo, simboloTabla: SimboloTabla) {
        if (objeto !is ElementoSimple) {
            agregarMensaje(infoCalculo, simbolo, "variable special solo almacena preguntas")
            return
        }
        simboloTabla.valor = objeto
    }
}