package com.example.pkm_forms_proyecto1.backend.color

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class ColorHSL(simbolo: Simbolo, val expr: Array<NodoExpresion>): TipoColor(simbolo) {
    override fun cadena(infoCalculo: InfoCalculo): String {
        val valores = Array(3 ){0}
        for (i in 0..2){
            valores[i] = validarNumber(infoCalculo, expr[i]).toInt()
            if(i != 0){
                if(valores[i] !in 0..100){
                    agregarMensaje(infoCalculo, simbolo, "valor hsl debe estar entre 0 y 100 para saturation y lightness")
                }
            }
        }
        return "<${valores[0]}, ${valores[1]}, ${valores[2]}>"
    }
}