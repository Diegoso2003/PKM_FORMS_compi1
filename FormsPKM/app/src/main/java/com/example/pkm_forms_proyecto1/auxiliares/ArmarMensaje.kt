package com.example.pkm_forms_proyecto1.auxiliares

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.enums.TipoError

fun armarMensaje(simbolo: Simbolo, mensaje: String): MensajeError{
    var mensajeError = MensajeError(TipoError.SEMANTICO)
    mensajeError.linea = simbolo.linea
    mensajeError.columna = simbolo.columna
    mensajeError.lexema = simbolo.lexema
    mensajeError.descripcion = mensaje
    return mensajeError
}

fun agregarMensaje(infoCalculo: InfoCalculo, simbolo: Simbolo, mensaje: String){
    infoCalculo.formulario.listaErrores.add(
        armarMensaje(
            simbolo,
            mensaje
        )
    )
}