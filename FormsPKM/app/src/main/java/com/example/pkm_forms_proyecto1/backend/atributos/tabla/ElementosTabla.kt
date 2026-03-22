package com.example.pkm_forms_proyecto1.backend.atributos.tabla

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.elementos.contenedores.Tabla
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import java.util.LinkedList

class ElementosTabla(simbolo: Simbolo, val elementos: LinkedList<LinkedList<NodoElemento>>) :
    Atributo(simbolo) {
    override fun agregarAtributo(
        infoCalculo: InfoCalculo, elemento: Elemento
    ) {
        if(elemento is Tabla){
            if(elemento.elementos.isNotEmpty()){
                agregarMensaje(infoCalculo, simbolo, "atributo duplicado.")
                return
            }
            elemento.elementosCadena.append("\t<content>\n")
            for(nodo in elementos){
                val lista = LinkedList<Elemento>()
                elemento.elementosCadena.append("\t\t<line>\n")
                for(elemento2 in nodo){
                    elemento2.realizarAccion(infoCalculo)
                    elemento.elementosCadena.append("\t\t<element>\n")
                    if(elemento2.elemento != null && elemento2.elemento!!.esElementoValido()){
                        lista.add(elemento2.elemento!!)
                        elemento.elementosCadena.append("\t\t\t")
                        elemento.elementosCadena.append(elemento2.elemento!!.aPkm())
                        elemento.elementosCadena.append("\n")
                        elemento2.elemento!!.aumentarContador(infoCalculo.formulario.contador)
                    } else {
                        agregarMensaje(infoCalculo, elemento2.simbolo, "error al agregar elemento a la tabla.")
                    }
                    elemento.elementosCadena.append("\t\t</element>\n")
                }
                elemento.elementosCadena.append("\t\t</line>\n")
                elemento.elementos.add(lista)
            }
            elemento.elementosCadena.append("\t</content>\n")
            return
        }
        agregarMensaje(infoCalculo, simbolo, "atributo elements solo aplicable a Tabla")
    }

}