package com.example.pkm_forms_proyecto1.backend.atributos.pregunta.opciones

import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.Atributo
import java.util.LinkedList

abstract class ParamOpci(simbolo: Simbolo): Atributo(simbolo) {
    abstract fun obtenerOpciones(infoCalculo: InfoCalculo, simbolo: Simbolo): LinkedList<String>
}