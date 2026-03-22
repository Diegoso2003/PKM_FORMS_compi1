package com.example.pkm_forms_proyecto1.backend.elementos.contenedores

import com.example.pkm_forms_proyecto1.backend.elementos.Elemento

abstract class Contenedor(nombre: String): Elemento(nombre) {
    var pointX: Double = -1.0
    var pointY: Double = -1.0

    override fun limpiar() {
        super.limpiar()
        pointY = -1.0
        pointX = -1.0
    }
}