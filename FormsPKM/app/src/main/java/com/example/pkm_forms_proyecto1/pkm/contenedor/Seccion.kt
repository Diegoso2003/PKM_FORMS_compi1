package com.example.pkm_forms_proyecto1.pkm.contenedor

import com.example.pkm_forms_proyecto1.enums.Orientacion
import com.example.pkm_forms_proyecto1.pkm.Nodoui
import java.util.LinkedList

class Seccion(width:Double, height:Double, pointX:Double, pointY:Double, val orientacion: Orientacion):Contenedor(width, height, pointX, pointY) {
    val elementos: MutableList<Nodoui> = LinkedList()

    fun agregarLista(lista: List<Nodoui>){
        elementos.addAll(lista)
    }
}