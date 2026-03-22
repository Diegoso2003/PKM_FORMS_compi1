package com.example.pkm_forms_proyecto1.backend.elementos.preguntas

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.enums.TipoError
import com.example.pkm_forms_proyecto1.excepciones.ErrorSemantico
import java.util.LinkedList

abstract class PreguntaConOpciones(nombre: String): Pregunta(nombre) {
    var options: LinkedList<String> = LinkedList()
    var correcto: LinkedList<Int> = LinkedList()

    fun validarOpciones(): Boolean{
        for(indice in correcto){
            if(indice < 0 || indice >= options.size) return false
        }
        return true
    }
}