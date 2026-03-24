package com.example.pkm_forms_proyecto1.pkm.contenedor

import com.example.pkm_forms_proyecto1.pkm.Nodoui
import java.util.LinkedList

class Tabla(width: Double, height: Double, pointX: Double, pointY: Double): Contenedor(width, height, pointX, pointY) {
    val lista: MutableList<MutableList<Nodoui>> = LinkedList()

    fun agregarElementos(lista: MutableList<LinkedList<Nodoui>>){
        this.lista.addAll(lista)
    }
}