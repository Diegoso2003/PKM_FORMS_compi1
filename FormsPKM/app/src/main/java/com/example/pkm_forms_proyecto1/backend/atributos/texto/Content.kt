package com.example.pkm_forms_proyecto1.backend.atributos.texto

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.Texto
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class Content(simbolo: Simbolo, val expr: NodoExpresion): Atributo(simbolo) {

    override fun agregarAtributo(infoCalculo: InfoCalculo, elemento: Elemento) {
        if(elemento is Texto){
            if(elemento.content.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido.")
                return
            }
            elemento.content = validarString(infoCalculo, expr)
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo content solo es aplicable para TEXT.")
    }

}