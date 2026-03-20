package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.LIMITE_CICLOS
import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.condiciones.Condicion
import java.util.LinkedList

class CicloDoWhile(simbolo: Simbolo, val condicion: Condicion, val lista: LinkedList<Accion>) :
    Accion(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        val inicio = infoCalculo.formulario.listaErrores.size
        var contador = 0
        do {
            contador++
            for (accion in lista) {
                accion.realizarAccion(infoCalculo)
            }
        } while (contador < LIMITE_CICLOS && condicion.evaluarCondicion(infoCalculo) && inicio == infoCalculo.formulario.listaErrores.size)
    }
}