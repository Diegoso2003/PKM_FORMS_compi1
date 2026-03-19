package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.enums.TipoError
import com.example.pkm_forms_proyecto1.excepciones.ErrorSemantico

abstract class NodoAritmetico(
    simbolo: Simbolo, val nodoIzq: NodoExpresion, val nodoDer: NodoExpresion, val nombreOpe: String
) : NodoExpresion(simbolo) {
    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        val expr1 = nodoIzq.evaluarNodo(infoCalculo)
        val expr2 = nodoIzq.evaluarNodo(infoCalculo)
        if (expr1.tipo == Tipo.ERROR_SEMANTICO || expr2.tipo == Tipo.ERROR_SEMANTICO) {
            return Expresion("error", Tipo.ERROR_SEMANTICO)
        }
        try {
            return realizarOperacion(expr1, expr2)
        } catch (e: ErrorSemantico) {
            infoCalculo.formulario.listaErrores.add(e.mensajeError)
            return Expresion("error", Tipo.ERROR_SEMANTICO)
        }
    }

    abstract fun realizarOperacion(expr1: Expresion, expr2: Expresion): Expresion

    protected fun mensajeDeError(
        expr: Expresion,
        descripcion: String = "variable de tipo ${expr.tipo.name.lowercase()} no soporta operacion ${nombreOpe}"
    ): MensajeError {
        val mensaje = MensajeError(TipoError.SEMANTICO)
        mensaje.linea = simbolo.linea
        mensaje.columna = simbolo.columna
        mensaje.lexema = simbolo.lexema
        mensaje.descripcion = descripcion
        return mensaje
    }
}