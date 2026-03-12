package com.example.pkm_forms_proyecto1.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pkm_forms_proyecto1.ui.vistas.EditorVista
import com.example.pkm_forms_proyecto1.ui.vistas.HomeVista

@Composable
fun AppNavegacion(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Ruta.Home.ruta
    ){
        composable(Ruta.Home.ruta){
            HomeVista(navController)
        }
        composable(Ruta.Editor.ruta){
            EditorVista(navController)
        }
    }
}