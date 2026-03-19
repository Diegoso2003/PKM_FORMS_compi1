package com.example.pkm_forms_proyecto1.backend.atributos.pregunta.opciones

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import java.util.LinkedList

class Correct(simbolo: Simbolo, val lista: LinkedList<NodoExpresion>): AtriPregunOpci(simbolo) {
}