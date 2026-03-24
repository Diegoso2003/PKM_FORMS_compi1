package com.example.pkm_forms_proyecto1.pkm.preguntas

import java.util.LinkedList

class PreguntaMultiple(
    width: Double,
    height: Double,
    label: String,
    val lista: MutableList<String>,
    val correct: MutableList<Double>
) : Pregunta(width, height, label) {
}