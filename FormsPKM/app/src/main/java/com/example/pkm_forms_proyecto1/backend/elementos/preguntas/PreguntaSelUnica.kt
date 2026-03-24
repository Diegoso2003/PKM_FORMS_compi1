package com.example.pkm_forms_proyecto1.backend.elementos.preguntas

import com.example.pkm_forms_proyecto1.backend.Contador

class PreguntaSelUnica: PreguntaConOpciones("SELECT_QUESTION") {
    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<select=${DAC(width)}, ${DAC(height)}, \"$label\",{")
        cadena.append(options.joinToString(", "){ "\"$it\"" })
        cadena.append("},")
        val indice = if(correcto.isNotEmpty()) correcto[0] else -1
        cadena.append(indice)
        if(estilo != null){
            cadena.append(">\n")
            cadena.append(estilo!!.aPkm())
            cadena.append("\n</select>")
        } else {
            cadena.append("/>\n")
        }
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return label.isNotBlank() && options.isNotEmpty() && correcto.size <= 1
    }

    override fun aumentarContador(contador: Contador) {
        contador.aumentarPreguntasSeleccion()
    }

}