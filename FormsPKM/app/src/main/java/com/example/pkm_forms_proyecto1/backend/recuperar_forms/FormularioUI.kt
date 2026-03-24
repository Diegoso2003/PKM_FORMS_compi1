package com.example.pkm_forms_proyecto1.backend.recuperar_forms

data class FormularioUI(
    val nombre: String,
    val autor: String? = null,
    val fecha: String? = null,
    val id: Int? = null,
    val esLocal: Boolean
)