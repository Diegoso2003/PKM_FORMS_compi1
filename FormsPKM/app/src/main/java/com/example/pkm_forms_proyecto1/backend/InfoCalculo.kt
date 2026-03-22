package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.nodos.Expresion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import java.util.LinkedList

class InfoCalculo(val formulario: Formulario) {
    val listaComodin: LinkedList<NodoExpresion> = LinkedList()
    var aceptaComodin = false
        private set
    var aceptaIds = true
        private set

    fun cambiarEstados(){
        aceptaIds = !aceptaIds
        aceptaComodin = !aceptaComodin
    }
}