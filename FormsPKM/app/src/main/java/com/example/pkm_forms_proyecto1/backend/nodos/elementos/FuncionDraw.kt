package com.example.pkm_forms_proyecto1.backend.nodos.elementos

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoElemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import java.util.LinkedList

class FuncionDraw(simbolo: Simbolo, val identi: String, val parametros: LinkedList<NodoExpresion>): NodoElemento(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {

    }
}