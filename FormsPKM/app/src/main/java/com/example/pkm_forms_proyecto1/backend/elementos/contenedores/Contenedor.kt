package com.example.pkm_forms_proyecto1.backend.elementos.contenedores

import com.example.pkm_forms_proyecto1.backend.elementos.Elemento

abstract class Contenedor(nombre: String): Elemento(nombre) {
    var pointX: Double? = null
    var pointY: Double? = null
}