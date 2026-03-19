package com.example.pkm_forms_proyecto1.backend.atributos.estilo

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.color.TipoColor
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.TipoBorde

class Borde(simbolo: Simbolo, val grosor: NodoExpresion, val tipo: TipoBorde, val color: TipoColor ): AtributoEstilo(simbolo) {
}