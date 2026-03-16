package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo

abstract class NodoExpresion() {
    abstract fun evaluarNodo(infoCalculo: InfoCalculo): Expresion
}