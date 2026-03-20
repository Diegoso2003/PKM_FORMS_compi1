package com.example.pkm_forms_proyecto1.backend.ciclos

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.Asignacion
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.condiciones.Condicion
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo

class ForNormal(
    simbolo: Simbolo,
    val valor: NodoExpresion,
    val condiciones: Condicion,
    val asignacion: Asignacion
) : TipoFor(simbolo) {

    override fun condicion(infoCalculo: InfoCalculo): Boolean {
        return condiciones.evaluarCondicion(infoCalculo)
    }

    override fun instruccionSiguiente(infoCalculo: InfoCalculo) {
        asignacion.realizarAccion(infoCalculo)
    }

    override fun calcularInicio(infoCalculo: InfoCalculo): Double {
        val expr = valor.evaluarNodo(infoCalculo)
        if(expr.tipo == Tipo.NUMBER){
            return expr.objeto as Double
        }
        if(expr.tipo == Tipo.ERROR_SEMANTICO) return 0.0
        agregarMensaje(infoCalculo, expr.simbolo, "variable de tipo number solo almacena números")
        return 0.0
    }

}