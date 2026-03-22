package com.example.pkm_forms_proyecto1.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pkm_forms_proyecto1.backend.Formulario
import com.example.pkm_forms_proyecto1.ui.vistas.DatosFormVista
import com.example.pkm_forms_proyecto1.ui.vistas.EditorVista
import com.example.pkm_forms_proyecto1.ui.vistas.HomeVista
import com.example.pkm_forms_proyecto1.ui.vistas.ReporteErrores

@Composable
fun AppNavegacion(){
    val navController = rememberNavController()
    val formulario by remember{ mutableStateOf( Formulario()) }
    NavHost(
        navController = navController,
        startDestination = Ruta.Home.ruta
    ){
        composable(Ruta.Home.ruta){
            HomeVista(navController)
        }
        composable(Ruta.Editor.ruta){
            EditorVista(navController, formulario)
        }
        composable(Ruta.Errores.ruta){
            ReporteErrores(navController, formulario)
        }
        composable(Ruta.Autor.ruta){
            DatosFormVista(navController, formulario)
        }
    }
}