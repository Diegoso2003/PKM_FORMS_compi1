package com.example.pkm_forms_proyecto1.backend.condiciones

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion

class CondicionExpresion(simbolo: Simbolo, val expr1: NodoExpresion): Condicion(simbolo) {
}