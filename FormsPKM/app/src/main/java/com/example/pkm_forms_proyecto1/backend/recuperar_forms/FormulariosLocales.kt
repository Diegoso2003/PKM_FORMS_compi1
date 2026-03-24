package com.example.pkm_forms_proyecto1.backend.recuperar_forms

import android.content.Context
import java.io.File

class FormulariosLocales(private val context: Context) {
    fun obtenerFormularios(): List<FormularioUI> {
        val carpeta = File(context.filesDir, "formularios")

        if (!carpeta.exists()) return emptyList()

        return carpeta.listFiles()?.filter { it.isDirectory }?.map {
                FormularioUI(
                    nombre = it.name, esLocal = true
                )
            } ?: emptyList()
    }

    fun obtenerPkm(nombreCarpeta: String): String? {
        val carpeta = File(context.filesDir, "formularios/$nombreCarpeta")

        return carpeta.listFiles()?.find { it.extension == "pkm" }?.readText()
    }

    fun obtenerForm(nombreCarpeta: String): String? {
        val carpeta = File(context.filesDir, "formularios/$nombreCarpeta")

        return carpeta.listFiles()?.find { it.extension == "form" }?.readText()
    }

}