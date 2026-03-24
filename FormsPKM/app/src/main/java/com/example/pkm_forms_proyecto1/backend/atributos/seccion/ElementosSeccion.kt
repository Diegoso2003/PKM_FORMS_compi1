package com.example.pkm_forms_proyecto1.backend.atributos.seccion

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.contenedores.Seccion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import java.util.LinkedList

class ElementosSeccion(simbolo: Simbolo, val elementos: LinkedList<NodoElemento>) :
    Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo, elemento: Elemento
    ) {
        if(elemento is Seccion){
            if(elemento.elementos.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo duplicado")
                return
            }
            for(element in elementos){
                element.realizarAccion(infoCalculo)
                if(element.elemento != null){
                    elemento.listaElementos.append("\n")
                    elemento.listaElementos.append(element.elemento!!.aPkm())
                    elemento.listaElementos.append("\n")
                    elemento.elementos.add(element.elemento!!)
                    element.elemento!!.aumentarContador(infoCalculo.formulario.contador)
                } else {
                    agregarMensaje(infoCalculo, element.simbolo, "error al agregar el elemento")
                }
            }
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo solo disponible para SECTION.")
    }

}