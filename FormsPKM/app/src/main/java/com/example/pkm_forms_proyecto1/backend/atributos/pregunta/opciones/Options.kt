package com.example.pkm_forms_proyecto1.backend.atributos.pregunta.opciones

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.preguntas.PreguntaConOpciones

class Options(simbolo: Simbolo, val opciones: ParamOpci): Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is PreguntaConOpciones){
            if(elemento.options.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido")
                return
            }
            elemento.options = opciones.obtenerOpciones(infoCalculo, simbolo)
            if(elemento.correcto.isNotEmpty() && !elemento.validarOpciones()){
                agregarMensaje(infoCalculo, simbolo, "la cantidad de respuestas no coincide con la respuesta correcta")
            }
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo options solo es valido para preguntas desplegables o de selección.")
    }
}