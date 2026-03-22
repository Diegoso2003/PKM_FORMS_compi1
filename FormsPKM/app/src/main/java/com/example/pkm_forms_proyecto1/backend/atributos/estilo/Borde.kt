package com.example.pkm_forms_proyecto1.backend.atributos.estilo

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.color.TipoColor
import com.example.pkm_forms_proyecto1.backend.elementos.Borde
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.Estilo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.TipoBorde

class Borde(simbolo: Simbolo, val grosor: NodoExpresion, val tipo: TipoBorde, val color: TipoColor ): AtributoEstilo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is Estilo){
            if(elemento.border != null){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido.")
                return
            }
            val expr = validarNumber(infoCalculo, grosor)
            if(!validarNumeroPositivo(expr)){
                agregarMensaje(infoCalculo, simbolo, "grosor invalido, debe ser un número positivo")
            }
            val borde = Borde(tipo, expr)
            borde.colorCadena = color.cadena(infoCalculo)
            elemento.border = borde
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo aplicable solo a elemento Styles")
    }
}