package com.example.pkm_forms_proyecto1.backend.elementos.contenedores

import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import java.util.LinkedList

class Tabla: Contenedor("TABLE") {
    var elementos: LinkedList<LinkedList<Elemento>>? = null
}