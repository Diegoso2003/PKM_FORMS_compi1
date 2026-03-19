package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.auxiliares.armarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.SimboloTabla
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.enums.TipoError

class Identificador(val nombre: String, simbolo: Simbolo) : NodoExpresion(simbolo) {

    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        if (!infoCalculo.aceptaIds) {
            infoCalculo.formulario.listaErrores.add(
                armarMensaje(
                    simbolo,
                    "variable special no permite el uso de variables"
                )
            )
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        if (!infoCalculo.formulario.tabla.existeSimbolo(nombre)) {
            infoCalculo.formulario.listaErrores.add(armarMensaje(simbolo, "variable no declarada"))
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        val simbolo: SimboloTabla = infoCalculo.formulario.tabla.obtenerSimbolo(nombre)
        if (simbolo.tipo == Tipo.SPECIAL) {
            infoCalculo.formulario.listaErrores.add(
                armarMensaje(
                    this.simbolo,
                    "no se permite realizar operaciones aritméticas con variable special"
                )
            )
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        val expr = Expresion(simbolo.valor, simbolo.tipo, this.simbolo)
        return expr
    }

}