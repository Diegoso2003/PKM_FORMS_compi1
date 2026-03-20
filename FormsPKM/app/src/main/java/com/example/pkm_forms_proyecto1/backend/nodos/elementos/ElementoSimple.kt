package com.example.pkm_forms_proyecto1.backend.nodos.elementos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import java.util.LinkedList

class ElementoSimple(simbolo: Simbolo, val elemento: Elemento, val linkedList: LinkedList<Atributo>): NodoElemento(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {

    }
}