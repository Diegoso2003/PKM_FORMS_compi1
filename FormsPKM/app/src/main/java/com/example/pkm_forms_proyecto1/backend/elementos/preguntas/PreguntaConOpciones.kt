package com.example.pkm_forms_proyecto1.backend.elementos.preguntas

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.enums.TipoError
import com.example.pkm_forms_proyecto1.excepciones.ErrorSemantico
import java.util.LinkedList

abstract class PreguntaConOpciones(nombre: String): Pregunta(nombre) {
    var options: LinkedList<String>? = null
    var correcto: LinkedList<Int>? = null

    protected fun validarOpciones(){
        for(e in correcto!!){
            if(e < 0 || e >= options!!.size){
                val mensaje = MensajeError(TipoError.SEMANTICO)
                mensaje.descripcion = "$e está fuera de rango"
                throw ErrorSemantico(mensaje)
            }
        }
    }
}