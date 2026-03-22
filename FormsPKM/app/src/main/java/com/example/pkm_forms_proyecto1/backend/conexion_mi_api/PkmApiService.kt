package com.example.pkm_forms_proyecto1.backend.conexion_mi_api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PkmApiService {
    @POST("form")
    suspend fun guardarFormulario(@Body archivo: ArchivoPkm): Response<Unit>

}