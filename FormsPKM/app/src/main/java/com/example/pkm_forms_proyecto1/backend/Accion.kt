package com.example.pkm_forms_proyecto1.backend

abstract class Accion(simbolo: Simbolo) {
    abstract fun realizarAccion(infoCalculo: InfoCalculo)
}