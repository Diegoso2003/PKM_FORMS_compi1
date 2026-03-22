package com.example.pkm_forms_proyecto1.ui.vistas


import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.NOMBRE_APP
import com.example.pkm_forms_proyecto1.backend.Formulario


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReporteErrores(
    navController: NavController,
    formulario: Formulario
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(NOMBRE_APP) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {

            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp)
                    .horizontalScroll(scrollState)
            ) {
                Text("Lexema", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Linea", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Columna", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Tipo", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text("Descripcion", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(formulario.listaErrores) { error ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(error.lexema, modifier = Modifier.weight(1f))
                        Text(error.linea.toString(), modifier = Modifier.weight(1f))
                        Text(error.columna.toString(), modifier = Modifier.weight(1f))
                        Text(error.tipoError.name, modifier = Modifier.weight(1f))
                        Text(error.descripcion, modifier = Modifier.weight(1f))
                    }
                    Divider()
                }
            }
        }
    }
}