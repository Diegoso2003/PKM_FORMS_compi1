package com.example.pkm_forms_proyecto1.ui.vistas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.NOMBRE_APP
import com.example.pkm_forms_proyecto1.R
import com.example.pkm_forms_proyecto1.ui.navegacion.Ruta
import com.example.pkm_forms_proyecto1.viewmodels.FormularioViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormularios(
    vm: FormularioViewModel,
    navController: NavController,
    local: Boolean
) {

    LaunchedEffect(Unit) {
        vm.cargarFormularios(local)
    }
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(NOMBRE_APP)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding
        ) {

            items(vm.formularios) { form ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = form.nombre,style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        if (!form.esLocal) {
                            Text("Autor: ${form.autor}")
                            Text("Fecha: ${form.fecha}")
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                24.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            if(form.esLocal){
                                TextButton(
                                    onClick = {

                                    }
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.editar_boton),
                                            contentDescription = "icono",
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            "Editar",
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                            val scope = rememberCoroutineScope()
                            TextButton(
                                onClick = {
                                    vm.responderFormulario(
                                        form,
                                        onSuccess = {
                                            navController.navigate(Ruta.Form.ruta)
                                        },
                                        onError = {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "Error al abrir el formulario",
                                                    duration = SnackbarDuration.Short
                                                )
                                            }
                                        }
                                    )
                                }
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.contestar_form),
                                        contentDescription = "icono",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        "Contestar form",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}