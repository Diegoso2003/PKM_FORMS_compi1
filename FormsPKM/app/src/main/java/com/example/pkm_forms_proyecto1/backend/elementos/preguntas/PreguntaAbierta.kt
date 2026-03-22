package com.example.pkm_forms_proyecto1.backend.elementos.preguntas

import com.example.pkm_forms_proyecto1.backend.Contador

class PreguntaAbierta: Pregunta("OPEN_QUESTION") {
    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<open=${DAC(width)}, ${DAC(height)}, $label")
        if(estilo != null){
            cadena.append(">\n")
            cadena.append(estilo!!.aPkm())
            cadena.append("\n<open/>")
        } else {
            cadena.append("/>\n")
        }
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return label.isNotBlank()
    }

    override fun aumentarContador(contador: Contador) {
        contador.aumentarPreguntasAbiertas()
    }

}