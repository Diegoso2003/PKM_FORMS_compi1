package com.example.pkm_forms_proyecto1.ui.vistas

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.example.pkm_forms_proyecto1.DROP_QUESTION
import com.example.pkm_forms_proyecto1.ESTILO
import com.example.pkm_forms_proyecto1.OPEN_QUESTION
import com.example.pkm_forms_proyecto1.SECTION
import com.example.pkm_forms_proyecto1.SELECT_QUESTION

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
            text = { Text("Sección") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    SECTION
                )

                onTextChange(nuevo)
            }
        )

        DropdownMenuItem(
            text = { Text("Pregunta abierta") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    OPEN_QUESTION
                )

                onTextChange(nuevo)
            }
        )
        DropdownMenuItem(
            text = { Text("Pregunta desplegable") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    DROP_QUESTION
                )

                onTextChange(nuevo)
            }
        )
        DropdownMenuItem(
            text = { Text("Pregunta de selección única") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    SELECT_QUESTION
                )

                onTextChange(nuevo)
            }
        )
        DropdownMenuItem(
            text = { Text("Estilos") },
            onClick = {
                ocultarse()

                val nuevo = insertarEnCursor(
                    textState,
                    ESTILO
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