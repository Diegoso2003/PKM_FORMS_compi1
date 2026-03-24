package com.example.pkm_forms_proyecto1.ui.vistas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.NOMBRE_APP
import com.example.pkm_forms_proyecto1.ui.navegacion.Ruta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeVista(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(NOMBRE_APP)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Ruta.Editor.ruta)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editor de código")
            }
            Button(
                onClick = {
                    navController.navigate(Ruta.Local.ruta)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Formularios creados")
            }
            Button(
                onClick = {
                    navController.navigate(Ruta.Repo.ruta)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Formularios del servidor")
            }
        }
    }
}