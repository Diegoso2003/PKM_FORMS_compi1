package com.example.pkm_forms_proyecto1.pkm.preguntas

import java.util.LinkedList

class PreguntaSeleccion(
width: Double,
height: Double,
label: String,
val lista: MutableList<String>,
val correct: Double
) : Pregunta(width, height, label) {
}