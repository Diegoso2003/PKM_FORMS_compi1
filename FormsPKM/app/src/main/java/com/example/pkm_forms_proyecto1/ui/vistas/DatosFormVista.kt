package com.example.pkm_forms_proyecto1.ui.vistas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.backend.Formulario
import com.example.pkm_forms_proyecto1.backend.guardado_archivos.FormularioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatosFormVista(
    navController: NavController, formulario: Formulario,
    viewModel: FormularioViewModel = viewModel()
) {
    var autor by remember { mutableStateOf("") }
    var nombreFormulario by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    var mensajeError by remember { mutableStateOf<String?>(null) }
    var mensajeExito by remember { mutableStateOf<String?>(null) }
    val resultado = viewModel.resultado
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Crear Formulario") }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }
            })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Información del Formulario",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = autor,
                onValueChange = {
                    autor = it
                    mensajeError = null
                },
                label = { Text("Autor") },
                placeholder = { Text("Ingrese el nombre del autor") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = mensajeError != null && autor.isBlank()
            )

            OutlinedTextField(
                value = nombreFormulario,
                onValueChange = {
                    nombreFormulario = it
                    mensajeError = null
                },
                label = { Text("Nombre del Formulario") },
                placeholder = { Text("Ingrese el nombre del formulario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = mensajeError != null && nombreFormulario.isBlank()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                    mensajeError = null
                },
                label = { Text("Descripción") },
                placeholder = { Text("Ingrese una descripción del formulario") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )

            if (mensajeError != null) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ), modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = mensajeError!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            if (mensajeExito != null) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ), modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = mensajeExito!!,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        autor = ""
                        nombreFormulario = ""
                        descripcion = ""
                        mensajeError = null
                        mensajeExito = null
                    }, modifier = Modifier.weight(1f)
                ) {
                    Text("Limpiar")
                }

                Button(
                    onClick = {
                        when {
                            autor.isBlank() -> {
                                mensajeError = "Por favor ingrese el autor"
                                mensajeExito = null
                            }

                            !esNombreValido(nombreFormulario) -> {
                                mensajeError = obtenerMensajeErrorNombre(nombreFormulario)
                                mensajeExito = null
                            }

                            else -> {
                                val context = navController.context
                                formulario.agregarDatos(autor, nombreFormulario, descripcion)
                                viewModel.guardar(formulario, context)
                            }
                        }
                    }, modifier = Modifier.weight(1f)
                ) {
                    Text("Guardar")
                }

                resultado?.let {

                    val mensaje = when {
                        it.localOk && it.apiOk -> "Guardado en API y local"
                        it.localOk && !it.apiOk -> "Guardado solo localmente"
                        !it.localOk && it.apiOk -> "Guardado solo en API"
                        else -> "No se pudo guardar"
                    }

                    LaunchedEffect(resultado) {
                        snackbarHostState.showSnackbar(mensaje)
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

fun esNombreValido(nombre: String): Boolean {
    if (nombre.isBlank()) return false
    val caracteresInvalidos = setOf('/', '\\', ':', '*', '?', '"', '<', '>', '|', '\n', '\r', '\t')

    return !nombre.any { it in caracteresInvalidos } &&
            nombre != "." &&
            nombre != ".."
}

fun obtenerMensajeErrorNombre(nombre: String): String {
    return when {
        nombre.isBlank() -> "El nombre no puede estar vacío"
        nombre.contains('/') || nombre.contains('\\') -> "El nombre no puede contener / o \\"
        nombre.any { it in setOf(':', '*', '?', '"', '<', '>', '|') } ->
            "El nombre contiene caracteres no permitidos: : * ? \" < > |"
        nombre == "." || nombre == ".." -> "El nombre no puede ser . o .."
        else -> "ingresar un nombre valido"
    }
}