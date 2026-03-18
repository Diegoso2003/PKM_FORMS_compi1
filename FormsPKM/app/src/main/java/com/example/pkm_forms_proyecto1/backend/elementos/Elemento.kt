package com.example.pkm_forms_proyecto1.backend.elementos

abstract class Elemento(val nombre: String) {
    var width: Double? = null
    var height: Double? = null
    var estilo: Estilo? = null
}