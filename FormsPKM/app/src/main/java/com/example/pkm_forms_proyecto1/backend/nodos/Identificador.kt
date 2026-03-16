package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.SimboloTabla
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.enums.TipoError

class Identificador(val nombre: String, val linea: Int, val columna: Int) : NodoExpresion() {

    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        var mensajeError = MensajeError(TipoError.SEMANTICO)
        mensajeError.linea = linea
        mensajeError.columna = columna
        mensajeError.lexema = nombre
        if (!infoCalculo.aceptaIds()) {
            mensajeError.descripcion = "variable special no permite el uso de variables"
            infoCalculo.listaErrores.add(mensajeError)
            return Expresion("error", Tipo.ERROR_SEMANTICO)
        }
        if (!infoCalculo.tablaSimbolos!!.existeSimbolo(nombre)) {
            mensajeError.descripcion = "variable no declarada"
            infoCalculo.listaErrores.add(mensajeError)
            return Expresion("error", Tipo.ERROR_SEMANTICO)
        }
        var simbolo: SimboloTabla = infoCalculo.tablaSimbolos.obtenerSimbolo(nombre)
        if (simbolo.tipo == Tipo.SPECIAL) {
            mensajeError.descripcion =
                "operaciones aritméticas no soportados por variable tipo special"
            return Expresion("error", Tipo.ERROR_SEMANTICO)
        }
        var expr = Expresion(simbolo.valor, simbolo.tipo, linea, columna)
        expr.nombreID = nombre
        return expr
    }
}