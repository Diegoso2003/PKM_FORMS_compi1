package com.example.pkm_forms_proyecto1.ui.navegacion

sealed class Ruta(val ruta: String) {
    object Home: Ruta("home")
    object Editor: Ruta("editor")
}