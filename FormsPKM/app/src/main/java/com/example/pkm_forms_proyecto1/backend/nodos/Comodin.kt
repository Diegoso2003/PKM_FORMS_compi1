package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.auxiliares.armarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.enums.TipoError

class Comodin(simbolo: Simbolo) : NodoExpresion(simbolo) {

    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {

        if (!infoCalculo.aceptaComodin) {
            infoCalculo.formulario.listaErrores.add(
                armarMensaje(
                    simbolo,
                    "uso incorrecto de comodín"
                )
            )
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        if (infoCalculo.listaComodin.isEmpty()) {
            infoCalculo.formulario.listaErrores.add(
                armarMensaje(
                    simbolo,
                    "El número de argumentos pasados no coincide con los '?' definidos en la pregunta special, faltan parametros."
                )
            )
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        val exprAux = infoCalculo.listaComodin.removeFirst()
        infoCalculo.cambiarEstados()
        val exprFinal = exprAux.evaluarNodo(infoCalculo)
        infoCalculo.cambiarEstados()
        return exprFinal
    }
}