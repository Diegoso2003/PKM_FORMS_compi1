package com.example.pkm_forms_proyecto1.backend.atributos.pregunta.opciones

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.preguntas.PreguntaConOpciones
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import java.util.LinkedList

class Correct(simbolo: Simbolo, val lista: LinkedList<NodoExpresion>): Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is PreguntaConOpciones){
            if(elemento.correcto.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido.")
                return
            }
            if(lista.isEmpty()){
                agregarMensaje(infoCalculo, simbolo, "debe haber almenos una respuesta correcta.")
                return
            }
            for (nodoExpr in lista){
                val expr = validarNumber(infoCalculo, nodoExpr)
                if(validarNumeroEntero(expr) && validarNumeroPositivo(expr)){
                    elemento.correcto.add(expr.toInt())
                } else {
                    agregarMensaje(infoCalculo, nodoExpr.simbolo, "valor de indice no valido")
                    elemento.correcto.add(0)
                }
            }
            if(elemento.options.isNotEmpty() && !elemento.validarOpciones()){
                agregarMensaje(infoCalculo, simbolo, "valor de correcto fuera de rango de las opciones")
            }
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo solo valido para preguntas multiples y de selección.")
    }
}