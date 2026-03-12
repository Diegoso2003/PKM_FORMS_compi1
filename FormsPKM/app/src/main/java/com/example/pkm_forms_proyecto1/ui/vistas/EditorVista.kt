package com.example.pkm_forms_proyecto1.ui.vistas

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.NOMBRE_APP
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorVista(navController: NavController){
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    //var tokens by remember { mutableStateOf(listOf<Token>()) } // Descomentado
    var job by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val (linea, columna) = obtenerLineaColumna(textState.text, textState.selection.start)

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
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = MaterialTheme.shapes.medium
                    )
                    .background(Color.Black)
            ) {
                BasicTextField(
                    value = textState,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(12.dp),
                    onValueChange = {
                        textState = it
                        job?.cancel()
                        job = scope.launch {
                            delay(150)
                            //tokens = lexer(it.text) // Descomentado
                        }
                    },
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        fontSize = 14.sp
                    ),
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            /**Text(
                                text = colorearTexto(textState.text, tokens),
                                color = Color.White,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxSize()
                            )*/
                            innerTextField()
                        }
                    }
                )
            }

            Text(text = "Línea: $linea   Columna: $columna")
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        // abrir archivo .form
                    }
                ) {
                    Text("Abrir archivo")
                }

                Button(
                    onClick = {
                        // crear formulario
                    }
                ) {
                    Text("Crear formulario")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
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
/*
fun colorearTexto(texto: String, tokens: List<Token>): AnnotatedString {

    val builder = AnnotatedString.Builder(texto)

    tokens.forEach { token ->

        val color = when (token.tipo) {
            TipoToken.KEYWORD -> ColoresLexer.KEYWORD
            TipoToken.STRING -> ColoresLexer.STRING
            TipoToken.NUMBER -> ColoresLexer.NUMBER
            TipoToken.COMMENT -> ColoresLexer.COMMENT
            TipoToken.IDENTIFIER -> ColoresLexer.IDENTIFIER
            TipoToken.OPERATOR -> ColoresLexer.OPERATOR
        }

        builder.addStyle(
            SpanStyle(color = color),
            token.start,
            token.end
        )
    }

    return builder.toAnnotatedString()
}*/