package com.example.pkm_forms_proyecto1.backend.atributos

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.Tipo
import java.util.Locale

abstract class Atributo(val simbolo: Simbolo) {
    abstract fun agregarAtributo(infoCalculo: InfoCalculo, elemento: Elemento)

    protected fun validarString(infoCalculo: InfoCalculo, nodoExpresion: NodoExpresion): String{
        val expr = nodoExpresion.evaluarNodo(infoCalculo)
        when (expr.tipo){
            Tipo.STRING -> {
                val cadena = expr.objeto.toString()
                if(cadena.isBlank()){
                    agregarMensaje(infoCalculo, simbolo, "cadena vacia no aceptada.")
                    return "error"
                }
                return cadena
            }
            Tipo.ERROR_SEMANTICO -> return "error"
            Tipo.NUMBER, Tipo.SPECIAL -> {
                agregarMensaje(infoCalculo, simbolo, "este atributo solo acepta variables tipo string")
                return "error"
            }
        }
    }

    protected fun validarNumber(infoCalculo: InfoCalculo, nodoExpresion: NodoExpresion): Double{
        val expr = nodoExpresion.evaluarNodo(infoCalculo)
        when (expr.tipo){
            Tipo.NUMBER -> return expr.objeto as Double
            Tipo.ERROR_SEMANTICO -> return 0.0
            Tipo.STRING, Tipo.SPECIAL -> {
                agregarMensaje(infoCalculo, simbolo, "este atributo solo acepta variables tipo string")
                return 0.0
            }
        }
    }

    protected fun validarNumeroPositivo(numero: Double): Boolean{
        return numero >= 0.0
    }

    protected fun validarNumeroEntero(numero: Double): Boolean{
        val cadena = String.format(Locale.US, "%.2f", numero)
        val decimales = cadena.split(".")
        for(decimal in decimales[1]){
            if(decimal != '0'){
                return false
            }
        }
        return true
    }
}