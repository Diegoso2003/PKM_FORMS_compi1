package com.example.pkm_forms_proyecto1.backend.elementos.contenedores

import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.enums.Orientacion
import java.util.LinkedList

class Seccion:Contenedor("SECTION") {
    var orientacion: Orientacion? = null
    var elementos: LinkedList<Elemento>? = null
}