package com.example.pkm_forms_proyecto1.backend.atributos.general

import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.atributos.estilo.AtributoEstilo
import com.example.pkm_forms_proyecto1.backend.elementos.Estilo
import java.util.LinkedList

class Style(simbolo: Simbolo, val atributos: LinkedList<AtributoEstilo>) :
    AtributoGeneral(simbolo) {
    var estilo: Estilo = Estilo()
}