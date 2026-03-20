package com.example.pkm_forms_proyecto1.backend.condiciones

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.enums.Tipo

abstract class Condicion(val simbolo: Simbolo) {
    abstract fun evaluarCondicion(infoCalculo: InfoCalculo):Boolean
}