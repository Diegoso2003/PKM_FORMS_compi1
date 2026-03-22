package com.example.pkm_forms_proyecto1.backend.atributos.general

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.atributos.estilo.AtributoEstilo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.Estilo
import com.example.pkm_forms_proyecto1.backend.elementos.contenedores.Contenedor
import java.util.LinkedList

class Style(simbolo: Simbolo, val atributos: LinkedList<AtributoEstilo>) :
    Atributo(simbolo) {
    var estilo: Estilo = Estilo()
    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
        if(elemento.estilo != null){
            agregarMensaje(infoCalculo, simbolo, "atributo repetido.")
            return
        }
        for(estyle in atributos){
            estyle.agregarAtributo(infoCalculo, estilo)
        }
        if(elemento !is Contenedor && estilo.border != null){
            agregarMensaje(infoCalculo, simbolo, "atributo border solo aplicable a tablas y secciones")
        }
        elemento.estilo = estilo
    }
}