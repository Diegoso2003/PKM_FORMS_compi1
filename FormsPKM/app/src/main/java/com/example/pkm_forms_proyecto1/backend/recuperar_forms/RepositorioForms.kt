package com.example.pkm_forms_proyecto1.backend.recuperar_forms

import com.example.pkm_forms_proyecto1.backend.conexion_mi_api.PkmApiService

class RepositorioForms(
    private val local: FormulariosLocales,
    private val api: PkmApiService
) {

    suspend fun obtenerLocales(): List<FormularioUI>{
        return local.obtenerFormularios();
    }

    suspend fun obtenerApi(): List<FormularioUI> {
        val remotos = try {
            api.obtenerFormularios().map {
                FormularioUI(
                    nombre = it.nombre,
                    autor = it.autor,
                    fecha = it.fecha,
                    id = it.id,
                    esLocal = false
                )
            }
        } catch (e: Exception) {
            emptyList()
        }

        return remotos
    }

    suspend fun obtenerContenido(form: FormularioUI): String? {
        return if (form.esLocal) {
            local.obtenerPkm(form.nombre)
        } else {
            try {
                api.obtenerFormulario(form.id!!).pkm
            } catch (e: Exception) {
                null
            }
        }
    }

    fun obtenerFormLocal(nombre: String): String? {
        return local.obtenerForm(nombre)
    }
}