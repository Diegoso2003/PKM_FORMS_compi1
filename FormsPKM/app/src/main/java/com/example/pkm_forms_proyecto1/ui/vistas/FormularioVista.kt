package com.example.pkm_forms_proyecto1.ui.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkm_forms_proyecto1.NOMBRE_APP
import com.example.pkm_forms_proyecto1.enums.Orientacion
import com.example.pkm_forms_proyecto1.enums.TLetra
import com.example.pkm_forms_proyecto1.enums.TipoBorde
import com.example.pkm_forms_proyecto1.pkm.Border
import com.example.pkm_forms_proyecto1.pkm.Estiloui
import com.example.pkm_forms_proyecto1.pkm.Nodoui
import com.example.pkm_forms_proyecto1.pkm.Texto
import com.example.pkm_forms_proyecto1.pkm.contenedor.Seccion
import com.example.pkm_forms_proyecto1.pkm.contenedor.Tabla
import com.example.pkm_forms_proyecto1.pkm.preguntas.PreguntaAbierta
import com.example.pkm_forms_proyecto1.pkm.preguntas.PreguntaDesplegable
import com.example.pkm_forms_proyecto1.pkm.preguntas.PreguntaMultiple
import com.example.pkm_forms_proyecto1.pkm.preguntas.PreguntaSeleccion
import com.example.pkm_forms_proyecto1.viewmodels.FormularioViewModel

@Composable
fun FormularioVista(
    nodo: Nodoui,
    estiloPadre: Estiloui? = null
) {
    when (nodo) {
        is Seccion -> {
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)
            val modifier = Modifier
                .width(nodo.width.dp)
                .height(nodo.height.dp)
            if (nodo.orientacion == Orientacion.VERTICAL) {
                Column(modifier) {
                    nodo.elementos.forEach {
                        FormularioVista(it, estiloFinal)
                    }
                }
            } else {
                Row(modifier) {
                    nodo.elementos.forEach {
                        FormularioVista(it, estiloFinal)
                    }
                }
            }
        }

        is Tabla -> {
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)

            Column {
                nodo.lista.forEach { fila ->
                    Row {
                        fila.forEach {
                            FormularioVista(it, estiloFinal)
                        }
                    }
                }
            }
        }

        is Texto -> {
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)

            Text(
                text = nodo.label,
                fontSize = estiloFinal.textSize.sp,
                color = estiloFinal.color.color,
                fontFamily = when(estiloFinal.fuente){
                    TLetra.MONO -> FontFamily.Monospace
                    TLetra.SANS_SERIF -> FontFamily.SansSerif
                    TLetra.CURSIVE -> FontFamily.Cursive
                },
                modifier = Modifier.aplicarEstilos(estiloFinal)
            )
        }

        is PreguntaAbierta -> {
            var value by remember { mutableStateOf("") }
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)
            Column (
                modifier = Modifier.aplicarEstilos(estiloFinal)
            ){
                Text(text = nodo.label,
                    fontSize = estiloFinal.textSize.sp,
                    color = estiloFinal.color.color,
                    fontFamily = when(estiloFinal.fuente){
                        TLetra.MONO -> FontFamily.Monospace
                        TLetra.SANS_SERIF -> FontFamily.SansSerif
                        TLetra.CURSIVE -> FontFamily.Cursive
                    }
                )
                TextField(value = value, onValueChange = { value = it })
            }
        }

        is PreguntaSeleccion -> {
            var selected by remember { mutableStateOf(0) }
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)
            Column(
                modifier = Modifier.aplicarEstilos(estiloFinal)
            ) {
                Text(text = nodo.label,
                    fontSize = estiloFinal.textSize.sp,
                    color = estiloFinal.color.color,
                    fontFamily = when(estiloFinal.fuente){
                        TLetra.MONO -> FontFamily.Monospace
                        TLetra.SANS_SERIF -> FontFamily.SansSerif
                        TLetra.CURSIVE -> FontFamily.Cursive
                    })

                nodo.lista.forEachIndexed { i, opcion ->
                    Row {
                        RadioButton(
                            selected = selected == i,
                            onClick = { selected = i }
                        )
                        Text(opcion)
                    }
                }
            }
        }

        is PreguntaMultiple -> {
            val seleccionados = remember { mutableStateListOf<Int>() }
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)
            Column(
                modifier = Modifier.aplicarEstilos(estiloFinal)
            ) {
                Text(text = nodo.label,
                    fontSize = estiloFinal.textSize.sp,
                    color = estiloFinal.color.color,
                    fontFamily = when(estiloFinal.fuente){
                        TLetra.MONO -> FontFamily.Monospace
                        TLetra.SANS_SERIF -> FontFamily.SansSerif
                        TLetra.CURSIVE -> FontFamily.Cursive
                    })

                nodo.lista.forEachIndexed { i, opcion ->
                    Row {
                        Checkbox(
                            checked = seleccionados.contains(i),
                            onCheckedChange = {
                                if (it) seleccionados.add(i)
                                else seleccionados.remove(i)
                            }
                        )
                        Text(opcion)
                    }
                }
            }
        }

        is PreguntaDesplegable -> {
            var expanded by remember { mutableStateOf(false) }
            var selected by remember { mutableStateOf(0) }
            val estiloFinal = combinarEstilos(estiloPadre, nodo.estilo)
            Column(
                modifier = Modifier.aplicarEstilos(estiloFinal)
            ){
                Text(text = nodo.label,
                    fontSize = estiloFinal.textSize.sp,
                    color = estiloFinal.color.color,
                    fontFamily = when(estiloFinal.fuente){
                        TLetra.MONO -> FontFamily.Monospace
                        TLetra.SANS_SERIF -> FontFamily.SansSerif
                        TLetra.CURSIVE -> FontFamily.Cursive
                    })

                Box {
                    Button(onClick = { expanded = true }) {
                        Text(nodo.lista.getOrNull(selected) ?: "Seleccionar")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        nodo.lista.forEachIndexed { i, opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    selected = i
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaForm(
    vm: FormularioViewModel,
    navController: NavController
) {
    val formularioActual = vm.formularioActual
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
            items(formularioActual) { nodo ->
                FormularioVista(nodo, null)
            }
        }
    }
}

fun combinarEstilos(padre: Estiloui?, hijo: Estiloui?): Estiloui {
    return hijo ?: padre!!
}

fun Modifier.aplicarEstilos(
    estilo: Estiloui,
    tieneBorder: Boolean = false
): Modifier {
    var mod = this
    mod = mod.background(estilo.back.color)
    if (tieneBorder && estilo.border != null) {
        mod = mod.aplicarBorder(estilo.border)
    }
    return mod
}

fun Modifier.aplicarBorder(border: Border?): Modifier {
    if (border == null) return this

    return when (border.tipoBorde) {
        TipoBorde.LINE -> {
            this.border(
                width = border.grosor.dp,
                color = border.color.color
            )
        }
        TipoBorde.DOTTED -> {
            this.drawBehind {
                drawRect(
                    color = border.color.color,
                    style = Stroke(
                        width = border.grosor.toFloat(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f),
                            0f
                        )
                    )
                )
            }
        }
        TipoBorde.DOUBLE -> {
            this
                .border(border.grosor.dp, border.color.color)
                .padding(2.dp)
                .border(border.grosor.dp, border.color.color)
        }
    }
}