package com.example.pkm_forms_proyecto1.backend.atributos.estilo

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.Estilo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class TextSize(simbolo: Simbolo, val valor: NodoExpresion): AtributoEstilo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is Estilo){
            if(elemento.textSize > 0.0){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido")
                return
            }
            val expr = validarNumber(infoCalculo, valor)
            if(!validarNumeroPositivo(expr) || expr == 0.0){
                elemento.textSize = 1.0
                agregarMensaje(infoCalculo, simbolo, "text size no valido, debe ser mayor a cero")
                return
            }
            elemento.textSize = expr
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo aplicable solo a elemento Styles")
    }
}