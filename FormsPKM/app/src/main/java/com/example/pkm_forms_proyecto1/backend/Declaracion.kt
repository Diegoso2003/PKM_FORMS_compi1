package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.enums.Tipo

class Declaracion(val simbolo: Simbolo, val objeto: Any?, val identi: String, val tipo: Tipo): Accion(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {

    }
}