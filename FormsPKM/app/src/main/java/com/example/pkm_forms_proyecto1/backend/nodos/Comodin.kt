package com.example.pkm_forms_proyecto1.backend.nodos

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.enums.Tipo
import com.example.pkm_forms_proyecto1.enums.TipoError

class Comodin(val linea: Int, val columna: Int) : NodoExpresion() {

    override fun evaluarNodo(infoCalculo: InfoCalculo): Expresion {
        var mensaje = MensajeError(TipoError.SEMANTICO)
        if (!infoCalculo.aceptaComodines()) {
            mensaje.linea = linea
            mensaje.columna = columna
            mensaje.descripcion = "Uso incorrecto de comodin"
            mensaje.lexema = "?"
            infoCalculo.listaErrores.add(mensaje)
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        if (infoCalculo.listaComodin?.size == 0) {
            mensaje.linea = infoCalculo.linea
            mensaje.columna = infoCalculo.columna
            mensaje.lexema = "draw"
            mensaje.descripcion =
                "El número de argumentos no coincide con los '?' definidos en la pregunta special"
            return Expresion("Error", Tipo.ERROR_SEMANTICO)
        }
        return infoCalculo.listaComodin!!.removeFirst()
    }
}