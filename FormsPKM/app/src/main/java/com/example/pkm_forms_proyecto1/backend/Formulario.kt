package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import java.util.LinkedList

class Formulario {
    val tabla: TablaSimbolos = TablaSimbolos()
    val listaErrores: LinkedList<MensajeError> = LinkedList()
}