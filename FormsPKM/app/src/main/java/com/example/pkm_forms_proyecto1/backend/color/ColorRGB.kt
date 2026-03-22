package com.example.pkm_forms_proyecto1.backend.color

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class ColorRGB(simbolo: Simbolo, val exprs: Array<NodoExpresion>): TipoColor(simbolo) {
    override fun cadena(infoCalculo: InfoCalculo): String {
        val valores = Array(3 ){0}
        for (i in 0..2){
            valores[i] = validarNumber(infoCalculo, exprs[i]).toInt()
            if(valores[i] !in 0..255){
                agregarMensaje(infoCalculo, simbolo, "valor rgb debe estar entre 0 y 255")
            }
        }
        return "(${valores[0]}, ${valores[1]}, ${valores[2]})"
    }
}