package com.example.pkm_forms_proyecto1.backend.nodos.elementos

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.contenedores.Contenedor
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import java.util.LinkedList

class ElementoSimple(simbolo: Simbolo, val elemento2: Elemento, val atributos: LinkedList<Atributo>): NodoElemento(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        elemento2.limpiar()
        for(atributo in atributos){
            atributo.agregarAtributo(infoCalculo, elemento2)
        }
        elemento = elemento2
        if(!elemento2.esElementoValido()){
            agregarMensaje(infoCalculo, simbolo,"verificar que el elemento tenga los atributos requeridos.")
            return
        }
        elemento2.aumentarContador(infoCalculo.formulario.contador)
        if(elemento2 is Contenedor){
            infoCalculo.formulario.datos.append(elemento2.aPkm())
        }
    }
}