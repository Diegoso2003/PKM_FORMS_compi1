package com.example.pkm_forms_proyecto1.backend.elementos.contenedores

import com.example.pkm_forms_proyecto1.backend.Contador
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import java.util.LinkedList

class Tabla: Contenedor("TABLE") {
    var elementos: LinkedList<LinkedList<Elemento>> = LinkedList()
    val elementosCadena = StringBuilder()

    override fun limpiar() {
        super.limpiar()
        elementos.clear()
        elementosCadena.clear()
    }

    override fun aPkm(): String {
        val cadena = StringBuilder()
        cadena.append("<table=${DAC(width)}, ${DAC(height)}, ${DAC(pointX)}, ${DAC(pointY)}>\n")
        if(estilo != null){
            cadena.append(estilo!!.aPkm())
        }
        cadena.append(elementosCadena).append("\n")
        cadena.append("</table>")
        return cadena.toString()
    }

    override fun esElementoValido(): Boolean {
        return width >= 0 && height >= 0 && pointX >= 0 && pointY >= 0 && elementos.isNotEmpty()
    }

    override fun aumentarContador(contador: Contador) {
        contador.aumentarTablas()
    }
}