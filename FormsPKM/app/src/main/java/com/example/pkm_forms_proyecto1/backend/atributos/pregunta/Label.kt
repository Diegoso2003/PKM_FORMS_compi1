package com.example.pkm_forms_proyecto1.backend.atributos.pregunta

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.preguntas.Pregunta
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class Label(simbolo: Simbolo, val expr: NodoExpresion): Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is Pregunta){
            if(elemento.label.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo duplicado")
                return
            }
            elemento.label = validarString(infoCalculo, expr)
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo solo disponible para preguntas")
    }
}