package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.condiciones.Condicion
import java.util.LinkedList

class EstructuraIf(simbolo: Simbolo, val condicion: Condicion, val lista: LinkedList<Accion>, condicional: Condicional): Condicional(simbolo, condicional) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        if(condicion.evaluarCondicion(infoCalculo)){
            for (accion in lista){
                accion.realizarAccion(infoCalculo)
            }
            return
        }
        condicional?.realizarAccion(infoCalculo)
    }
}