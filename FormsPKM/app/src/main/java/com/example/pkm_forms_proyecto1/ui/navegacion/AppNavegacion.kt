package com.example.pkm_forms_proyecto1.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pkm_forms_proyecto1.backend.Formulario
import com.example.pkm_forms_proyecto1.backend.conexion_mi_api.RetrofitClient
import com.example.pkm_forms_proyecto1.backend.poke_api.RetrofitInstance
import com.example.pkm_forms_proyecto1.backend.recuperar_forms.FormulariosLocales
import com.example.pkm_forms_proyecto1.backend.recuperar_forms.RepositorioForms
import com.example.pkm_forms_proyecto1.ui.vistas.DatosFormVista
import com.example.pkm_forms_proyecto1.ui.vistas.EditorVista
import com.example.pkm_forms_proyecto1.ui.vistas.HomeVista
import com.example.pkm_forms_proyecto1.ui.vistas.PantallaForm
import com.example.pkm_forms_proyecto1.ui.vistas.PantallaFormularios
import com.example.pkm_forms_proyecto1.ui.vistas.ReporteErrores
import com.example.pkm_forms_proyecto1.viewmodels.FormularioViewModel
import com.example.pkm_forms_proyecto1.viewmodels.FormularioViewModelFactory

@Composable
fun AppNavegacion(){
    val navController = rememberNavController()
    val formulario by remember{ mutableStateOf( Formulario()) }
    val context = LocalContext.current
    val local = FormulariosLocales(context)
    val api = RetrofitClient.api
    val repo = RepositorioForms(local, api)

    val factory = FormularioViewModelFactory(repo)
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
        composable(Ruta.Local.ruta){
            PantallaFormularios(vm = viewModel(factory = factory), navController, true)
        }
        composable(Ruta.Repo.ruta){
            PantallaFormularios(vm = viewModel(factory = factory), navController, false)
        }
        composable(Ruta.Form.ruta){
            PantallaForm(vm = viewModel(factory = factory), navController)
        }
    }
}