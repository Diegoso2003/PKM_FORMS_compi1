package com.example.pkm_forms_proyecto1.backend.operador_logico

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.condiciones.Condicion
import com.example.pkm_forms_proyecto1.backend.operador_logico.OperadorAnd
import java.util.LinkedList

abstract class OperadorLogico(simbolo: Simbolo): Condicion(simbolo) {
    val listaCondiciones = LinkedList<Condicion>();

    fun agregarCondicion(condicion: Condicion){
        listaCondiciones.add(condicion)
    }

    constructor(simbolo: Simbolo, condicion1: Condicion, condicion2: Condicion): this(simbolo){
        this.agregarCondicion(condicion1)
        this.agregarCondicion(condicion2)
    }

}