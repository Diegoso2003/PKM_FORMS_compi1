package com.example.pkm_forms_proyecto1.backend.atributos.estilo

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.Estilo
import com.example.pkm_forms_proyecto1.enums.TLetra

class FontFamily(simbolo: Simbolo, val tLetra: TLetra): AtributoEstilo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is Estilo){
            if(elemento.fontFamily != null){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido.")
                return
            }
            elemento.fontFamily = tLetra
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo aplicable solo a elemento Styles")
    }

}