package com.example.pkm_forms_proyecto1.ui.vistas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.NOMBRE_APP
import com.example.pkm_forms_proyecto1.R
import com.example.pkm_forms_proyecto1.backend.Coloreador
import com.example.pkm_forms_proyecto1.backend.Token

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorVista(navController: NavController){
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var tokens by remember { mutableStateOf(listOf<Token>()) }
    val (linea, columna) = obtenerLineaColumna(textState.text, textState.selection.start)

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->

        uri?.let {
            context.contentResolver.openInputStream(it)?.use { inputStream ->
                val contenido: String = inputStream.bufferedReader().readText()
                textState = TextFieldValue(contenido)
            }
        }
    }

    Scaffold(
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
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(20.dp)
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val annotated = remember(textState.text, tokens) {
                colorearTexto(textState.text, tokens)
            }
            BasicTextField(
                value = textState,
                onValueChange = {
                    textState = it
                    val coloreador = Coloreador()
                    tokens = coloreador.obtenerColor(it.text)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF1E1E1E))
                    .border(1.dp, Color.White)
                    .padding(12.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 18.sp
                ),
                cursorBrush = SolidColor(Color.White),
                visualTransformation = {
                    TransformedText(
                        annotated,
                        OffsetMapping.Identity
                    )
                }
            )

            Text(text = "Línea: $linea   Columna: $columna")
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    24.dp,
                    Alignment.CenterHorizontally
                )
            ) {
                TextButton(
                    onClick = { launcher.launch(arrayOf("*/*")) }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.folder_open),
                            contentDescription = "icono",
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Abrir archivo",
                            fontSize = 12.sp
                        )
                    }
                }

                TextButton(
                    onClick = {  }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.crear_form),
                            contentDescription = "icono",
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Crear form",
                            fontSize = 12.sp
                        )
                    }
                }

                TextButton(
                    onClick = {  }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.insertar),
                            contentDescription = "icono",
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Insertar",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

fun obtenerLineaColumna(texto: String, cursor: Int): Pair<Int, Int> {
    val pos = cursor.coerceIn(0, texto.length)
    val antesCursor = texto.substring(0, pos)
    val linea = antesCursor.count { it == '\n' } + 1
    val columna = pos - antesCursor.lastIndexOf('\n')
    return Pair(linea, columna)
}

fun colorearTexto(texto: String, tokens: List<Token>): AnnotatedString {
    val builder = AnnotatedString.Builder(texto)
    tokens.forEach { token ->
        builder.addStyle(
            SpanStyle(color = token.color.color),
            token.inicio.toInt(),
            token.fin.toInt()
        )
    }
    return builder.toAnnotatedString()
}