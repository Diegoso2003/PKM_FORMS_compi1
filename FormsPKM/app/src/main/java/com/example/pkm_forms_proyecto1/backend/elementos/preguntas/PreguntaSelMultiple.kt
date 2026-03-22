package com.example.pkm_forms_proyecto1.backend.elementos.preguntas

import com.example.pkm_forms_proyecto1.backend.Contador

class PreguntaSelMultiple: PreguntaConOpciones("MULTIPLE_QUESTION") {
    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<multiple=${DAC(width)}, ${DAC(height)}, \"$label\",{")
        cadena.append(options.joinToString(", "){ "\"$it\"" })
        cadena.append("},{${correcto.joinToString(", ")}}")
        if(estilo != null){
            cadena.append(">\n")
            cadena.append(estilo!!.aPkm())
            cadena.append("\n<multiple/>")
        } else {
            cadena.append("/>\n")
        }
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return label.isNotBlank() && options.isNotEmpty()
    }

    override fun aumentarContador(contador: Contador) {
        contador.aumentarPreguntasMultiples()
    }
}