package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo

abstract class NodoExpresion(val simbolo: Simbolo){
    abstract fun evaluarNodo(infoCalculo: InfoCalculo): Expresion
}