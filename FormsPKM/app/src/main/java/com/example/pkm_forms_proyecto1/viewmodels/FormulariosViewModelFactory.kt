package com.example.pkm_forms_proyecto1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pkm_forms_proyecto1.backend.recuperar_forms.RepositorioForms

class FormularioViewModelFactory(
    private val repo: RepositorioForms
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FormularioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FormularioViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}