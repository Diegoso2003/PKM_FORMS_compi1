package com.example.pkm_forms_proyecto1.backend

abstract class Accion(val simbolo: Simbolo) {
    abstract fun realizarAccion(infoCalculo: InfoCalculo)

}