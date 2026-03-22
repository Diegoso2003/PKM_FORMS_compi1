package com.example.pkm_forms_proyecto1.backend.guardado_archivos

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkm_forms_proyecto1.backend.Formulario
import com.example.pkm_forms_proyecto1.backend.conexion_mi_api.ArchivoPkm
import com.example.pkm_forms_proyecto1.backend.conexion_mi_api.PkmApiService
import com.example.pkm_forms_proyecto1.backend.conexion_mi_api.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.File

class FormularioViewModel : ViewModel() {

    var resultado by mutableStateOf<ResultadoGuardado?>(null)
        private set

    fun guardar(formulario: Formulario, context: Context) {
        viewModelScope.launch {
            val resultadoFinal = coroutineScope {

                val local = async {
                    try {
                        guardarLocal(formulario, context)
                        true
                    } catch (e: Exception) {
                        false
                    }
                }

                val api = async {
                    try {
                        val archivoPkm = ArchivoPkm(formulario.nombre, 0, formulario.autor, "", formulario.datos.toString())
                        val response = RetrofitClient.api.guardarFormulario(archivoPkm)
                        response.isSuccessful
                    } catch (e: Exception) {
                        e.printStackTrace()
                        false
                    }
                }

                ResultadoGuardado(
                    localOk = local.await(),
                    apiOk = api.await()
                )
            }

            resultado = resultadoFinal
        }
    }

    private fun guardarLocal(formulario: Formulario, context: Context) {
        val directorioBase = context.filesDir
        val carpetaFormularios = File(directorioBase, "formularios")
        if (!carpetaFormularios.exists()) {
            carpetaFormularios.mkdirs()
        }
        var carpetaFormulario = File(carpetaFormularios, formulario.nombre)
        var contador = 1

        while (carpetaFormulario.exists()) {
            carpetaFormulario = File(carpetaFormularios, "${formulario.nombre}_$contador")
            contador++
        }
        carpetaFormulario.mkdirs()
        val archivo = File(carpetaFormulario, "${formulario.nombre.trim()}.pkm")
        val archivoForm = File(carpetaFormulario, "${formulario.nombre.trim()}.form")
        archivo.writeText(formulario.datos.toString())
        archivoForm.writeText(formulario.textoForm)
    }
}