package com.example.pkm_forms_proyecto1.ui.navegacion

sealed class Ruta(val ruta: String) {
    object Home: Ruta("home")
    object Editor: Ruta("editor")
    object Errores: Ruta("errores")
    object Autor: Ruta("autor")
    object Local: Ruta("form_locales")
    object Repo: Ruta("form_api")
    object Form: Ruta("form_vista")
}