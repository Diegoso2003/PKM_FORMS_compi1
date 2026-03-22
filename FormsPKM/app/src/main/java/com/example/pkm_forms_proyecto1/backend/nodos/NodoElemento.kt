package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento

abstract class NodoElemento(simbolo: Simbolo): Accion(simbolo) {
    var elemento: Elemento? = null
}