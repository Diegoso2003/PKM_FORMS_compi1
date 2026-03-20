package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import java.util.LinkedList

class EstructuraElse(simbolo: Simbolo, val lista: LinkedList<Accion>): Condicional(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        for(accion in lista){
            accion.realizarAccion(infoCalculo)
        }
    }
}