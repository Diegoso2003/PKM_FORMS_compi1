package com.example.pkm_forms_proyecto1.backend.elementos

import com.example.pkm_forms_proyecto1.backend.Contador
import java.util.Locale

abstract class Elemento(val nombre: String) {
    var width: Double = -1.0
    var height: Double = -1.0
    var estilo: Estilo? = null
    abstract fun aPkm(): String
    abstract fun esElementoValido(): Boolean

    abstract fun aumentarContador(contador: Contador)

    protected fun DAC(numero: Double): String{
        return String.format(Locale.US, "%.2f", numero)
    }

    open fun limpiar(){
        width = -1.0
        height = -1.0
        estilo = null
    }
}