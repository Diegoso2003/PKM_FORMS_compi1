package com.example.pkm_forms_proyecto1.backend

class Contador {
    var tablas: Int = 0
    private set
    var secciones: Int = 0
    private set
    var preguntas: Int = 0
    private set
    var preguntasAbiertas: Int = 0
    private set
    var preguntasDesplegables: Int = 0
    private set
    var preguntasMultiples: Int = 0
    private set
    var preguntasSeleccion: Int = 0
    private set

    fun aumentarPreguntasSeleccion(){
        preguntasSeleccion++
        preguntas++
    }

    fun aumentarPreguntasMultiples(){
        preguntasMultiples++
        preguntas++
    }

    fun aumentarPreguntasDesplegables(){
        preguntasDesplegables++
        preguntas++
    }

    fun aumentarPreguntasAbiertas(){
        preguntas++
        preguntasAbiertas++
    }

    fun aumentarTablas(){
        tablas++
    }

    fun aumentarSecciones(){
        secciones++
    }
}