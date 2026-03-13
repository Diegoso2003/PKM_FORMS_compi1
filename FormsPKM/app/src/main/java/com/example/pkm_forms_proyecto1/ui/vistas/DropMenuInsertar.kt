package com.example.pkm_forms_proyecto1.ui.vistas

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun DropMenuInsertar(
    menuInsertar: Boolean,
    ocultarse: () -> Unit,
    textState: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit
){
    DropdownMenu(
        expanded = menuInsertar,
        onDismissRequest = ocultarse
    ) {

        DropdownMenuItem(
            text = { Text("Insertar input") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    "input nombre;\n"
                )

                onTextChange(nuevo)
            }
        )

        DropdownMenuItem(
            text = { Text("Insertar input") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    "input nombre;\n"
                )

                onTextChange(nuevo)
            }
        )
    }
}

fun insertarEnCursor(
    textState: TextFieldValue,
    textoInsertar: String
): TextFieldValue {

    val cursor = textState.selection.start

    val nuevoTexto =
        textState.text.substring(0, cursor) +
                textoInsertar +
                textState.text.substring(cursor)

    val nuevaPos = cursor + textoInsertar.length

    return TextFieldValue(
        text = nuevoTexto,
        selection = TextRange(nuevaPos)
    )
}