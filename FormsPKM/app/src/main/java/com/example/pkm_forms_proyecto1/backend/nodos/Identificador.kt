package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.SimboloTabla
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.enums.TipoError

class Identificador(val nombre: String, val linea: Int, val columna: Int) : NodoExpresion() {

    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        if (!infoCalculo.aceptaIds()) {
            return mensajeError(infoCalculo, "variable special no permite el uso de variables")
        }
        if (!infoCalculo.tablaSimbolos!!.existeSimbolo(nombre)) {
            return mensajeError(infoCalculo, "variable no declarada")
        }
        val simbolo: SimboloTabla = infoCalculo.tablaSimbolos.obtenerSimbolo(nombre)
        if (simbolo.tipo == Tipo.SPECIAL) {
            return mensajeError(
                infoCalculo,
                "no se permite realizar operaciones aritmeticas con varibles de tipo especial"
            )
        }
        val expr = Expresion(simbolo.valor, simbolo.tipo, linea, columna)
        expr.nombreID = nombre
        return expr
    }

    private fun mensajeError(infoCalculo: InfoCalculo, mensaje: String): Expresion {
        val mensajeError = MensajeError(TipoError.SEMANTICO)
        mensajeError.linea = linea
        mensajeError.columna = columna
        mensajeError.lexema = nombre
        mensajeError.descripcion = mensaje
        infoCalculo.listaErrores.add(mensajeError)
        return Expresion("error", Tipo.ERROR_SEMANTICO)
    }
}