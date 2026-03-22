package com.example.pkm_forms_proyecto1.backend.elementos

import com.example.pkm_forms_proyecto1.backend.Contador


class Texto: Elemento("TEXT") {
    var content: String = ""
    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<text=${DAC(width)}, ${DAC(height)}, \"$content\"")
        if(estilo == null){
            cadena.append("/>\n")
        } else {
            cadena.append(">\n\t")
            cadena.append(estilo!!.aPkm())
            cadena.append("\n")
            cadena.append("</text>")
        }
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return content.isNotEmpty()
    }

    override fun aumentarContador(contador: Contador) {

    }

    override fun limpiar() {
        super.limpiar()
        content = ""
    }
}