package com.example.pkm_forms_proyecto1.backend

import androidx.collection.MutableScatterMap

class TablaSimbolos {
    private val tabla = MutableScatterMap<String, SimboloTabla>()

    fun agregarALaTabla(id: String, simboloTabla: SimboloTabla){
        tabla[id] = simboloTabla
    }

    fun obtenerSimbolo(id: String): SimboloTabla{
        return tabla[id]!!
    }

    fun cambiarValor(id: String, simboloTabla: SimboloTabla){
        tabla[id] = simboloTabla
    }

    fun existeSimbolo(id: String): Boolean{
        return id in tabla
    }
}