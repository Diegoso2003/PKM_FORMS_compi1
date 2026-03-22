package com.example.pkm_forms_proyecto1.backend.nodos.elementos

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo
import java.util.LinkedList

class FuncionDraw(simbolo: Simbolo, val identi: String, val parametros: LinkedList<NodoExpresion>) :
    NodoElemento(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        if (!infoCalculo.formulario.tabla.existeSimbolo(identi)) {
            agregarMensaje(infoCalculo, simbolo, "variable no declarada.")
            return
        }
        val pregunta = infoCalculo.formulario.tabla.obtenerSimbolo(identi)
        if (pregunta.tipo != Tipo.SPECIAL) {
            agregarMensaje(infoCalculo, simbolo, "variable no es de tipo special.")
            return
        }
        val element = pregunta.valor as ElementoSimple
        infoCalculo.cambiarEstados()
        infoCalculo.listaComodin.clear()
        infoCalculo.listaComodin.addAll(parametros)
        element.realizarAccion(infoCalculo)
        if (infoCalculo.listaComodin.isNotEmpty()) {
            agregarMensaje(
                infoCalculo,
                simbolo,
                "La cantidad de parámetros no coincide con los comodines definidos en la pregunta, función draw paso parametros de más."
            )
        }
        infoCalculo.cambiarEstados()
        elemento = element.elemento
    }
}