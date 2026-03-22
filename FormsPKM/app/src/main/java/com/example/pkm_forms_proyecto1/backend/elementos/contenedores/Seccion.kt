package com.example.pkm_forms_proyecto1.backend.elementos.contenedores

import com.example.pkm_forms_proyecto1.backend.Contador
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.enums.Orientacion
import java.util.LinkedList

class Seccion:Contenedor("SECTION") {
    var orientacion: Orientacion? = null
    var elementos: LinkedList<Elemento> = LinkedList()
    val listaElementos = StringBuilder()

    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<section=${DAC(width)}, ${DAC(height)}, ${DAC(pointX)}, ${DAC(pointY)},")
        cadena.append((orientacion ?: Orientacion.VERTICAL).name).append(">")
        if(estilo != null){
            cadena.append(estilo!!.aPkm())
        }
        cadena.append("\n\t<content>\n$listaElementos\n\t</content>")
        cadena.append("</section>")
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return width >= 0 && height >= 0 && pointX >= 0 && pointY >= 0
    }

    override fun aumentarContador(contador: Contador) {
        contador.aumentarSecciones()
    }
}