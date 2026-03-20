package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo

abstract class Condicional(simbolo: Simbolo): Accion(simbolo) {
    var condicional: Condicional? = null
    constructor(simbolo: Simbolo, condicional: Condicional): this(simbolo){
        this.condicional = condicional
    }

}