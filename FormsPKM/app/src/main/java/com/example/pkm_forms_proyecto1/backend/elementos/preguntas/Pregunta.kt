package com.example.pkm_forms_proyecto1.backend.elementos.preguntas

import com.example.pkm_forms_proyecto1.backend.elementos.Elemento

abstract class Pregunta(nombre: String): Elemento(nombre) {
    var label: String = ""
}