package com.example.pkm_forms_proyecto1.backend.atributos.general

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class Width(simbolo: Simbolo, val nodoExpresion: NodoExpresion): Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento.width > 0.0){
            agregarMensaje(infoCalculo, simbolo, "atributo repetido.")
            return
        }
        val expr = validarNumber(infoCalculo, nodoExpresion)
        if(!validarNumeroPositivo(expr)){
            elemento.width = 1.0
            agregarMensaje(infoCalculo, simbolo, "valor de atributo no valido.")
        } else {
            elemento.width = expr
        }
    }
}