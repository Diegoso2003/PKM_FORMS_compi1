package com.example.pkm_forms_proyecto1.viewmodels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.backend.Accion
import com.example.pkm_forms_proyecto1.backend.recuperar_forms.FormularioUI
import com.example.pkm_forms_proyecto1.backend.recuperar_forms.RepositorioForms
import com.example.pkm_forms_proyecto1.enums.TipoError
import com.example.pkm_forms_proyecto1.pkm.Nodoui
import com.example.pkm_forms_proyecto1.pkm.analizadores.PkmLexer
import com.example.pkm_forms_proyecto1.pkm.analizadores.PkmParser
import kotlinx.coroutines.launch
import java.util.LinkedList

class FormularioViewModel(
    private val repo: RepositorioForms
) : ViewModel() {

    var formularios = mutableStateListOf<FormularioUI>()
        private set

    var formularioActual = mutableStateListOf<Nodoui>()
        private set

    var estado = mutableStateOf("idle")

    fun cargarFormularios(local: Boolean) {
        viewModelScope.launch {
            estado.value = "loading"
            val data = if(local) repo.obtenerLocales() else repo.obtenerApi()
            formularios.clear()
            formularios.addAll(data)
            estado.value = "idle"
        }
    }

    fun responderFormulario(
        form: FormularioUI,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val texto = repo.obtenerContenido(form)

            if (texto == null) {
                onError("No se pudo abrir el formulario")
                return@launch
            }

            val valido = analizar(texto)
            if (valido) {
                onSuccess()
            } else {
                onError("No se pudo abrir el formulario")
            }
        }
    }

    private fun analizar(texto: String): Boolean {
        formularioActual.clear()
        return try {
            val reader = java.io.StringReader(texto)
            val lexer = PkmLexer(reader)
            val parser = PkmParser(lexer)
            val result = parser.parse()
            val lista = result?.value
            if (lista is LinkedList<*>) {
                val nodos = lista.filterIsInstance<Nodoui>()
                formularioActual.addAll(nodos)
                return true
            }
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}