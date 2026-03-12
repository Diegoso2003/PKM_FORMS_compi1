package com.example.pkm_forms_proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pkm_forms_proyecto1.ui.navegacion.AppNavegacion
import com.example.pkm_forms_proyecto1.ui.theme.Pkm_forms_proyecto1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pkm_forms_proyecto1Theme {
                AppNavegacion()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pkm_forms_proyecto1Theme {
        AppNavegacion()
    }
}