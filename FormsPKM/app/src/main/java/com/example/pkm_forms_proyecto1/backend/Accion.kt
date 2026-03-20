package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.auxiliares.armarMensaje

abstract class Accion(val simbolo: Simbolo) {
    abstract fun realizarAccion(infoCalculo: InfoCalculo)

}