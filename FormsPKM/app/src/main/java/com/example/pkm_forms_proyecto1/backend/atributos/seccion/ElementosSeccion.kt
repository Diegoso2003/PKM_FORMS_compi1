package com.example.pkm_forms_proyecto1.backend.atributos.seccion

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import java.util.LinkedList

class ElementosSeccion(simbolo: Simbolo, val elementos: LinkedList<NodoElemento>) :
    AtributoSeccion(simbolo) {

}