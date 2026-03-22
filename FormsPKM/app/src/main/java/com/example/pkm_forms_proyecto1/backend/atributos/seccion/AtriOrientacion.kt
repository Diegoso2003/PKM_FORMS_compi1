package com.example.pkm_forms_proyecto1.backend.atributos.seccion

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.contenedores.Seccion
import com.example.pkm_forms_proyecto1.enums.Orientacion

class AtriOrientacion(simbolo: Simbolo, val orientacion: Orientacion): Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento is Seccion){
            if(elemento.orientacion != null){
                agregarMensaje(infoCalculo, simbolo, "atributo repetido")
            }
            elemento.orientacion = orientacion
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo valido solo para SECTION.")
    }
}