package com.example.pkm_forms_proyecto1.backend.atributos.tabla

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import java.util.LinkedList

class ElementosTabla(simbolo: Simbolo, val elementos: LinkedList<LinkedList<NodoElemento>>) :
    AtributoTabla(simbolo) {

}