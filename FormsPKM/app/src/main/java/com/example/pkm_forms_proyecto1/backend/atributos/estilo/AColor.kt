package com.example.pkm_forms_proyecto1.backend.atributos.estilo

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.color.TipoColor
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.Estilo

class AColor(simbolo: Simbolo, val tipoColor: TipoColor): AtributoEstilo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is Estilo){
            if(elemento.colorCadena.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido")
                return
            }
            elemento.colorCadena = tipoColor.cadena(infoCalculo)
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo aplicable solo a elemento Styles")
    }
}