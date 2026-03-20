package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.LIMITE_CICLOS
import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import java.util.LinkedList

class CicloFor(simbolo: Simbolo, val tipoFor: TipoFor, val lista: LinkedList<Accion>): Accion(simbolo) {
    override fun realizarAccion(infoCalculo: InfoCalculo) {
        val inicio = infoCalculo.formulario.listaErrores.size
        var contador = 0
        tipoFor.inicio(infoCalculo)
        var contador2 = infoCalculo.formulario.listaErrores.size
        while(contador < LIMITE_CICLOS && inicio == contador2 && tipoFor.condicion(infoCalculo)){
            contador++
            for(accion in lista){
                accion.realizarAccion(infoCalculo)
            }
            contador2 = infoCalculo.formulario.listaErrores.size
            tipoFor.instruccionSiguiente(infoCalculo)
        }
    }
}