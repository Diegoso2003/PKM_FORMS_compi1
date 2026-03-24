package com.example.pkm_forms_proyecto1.backend.conexion_mi_api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PkmApiService {
    @POST("form")
    suspend fun guardarFormulario(@Body archivo: ArchivoPkm): Response<Unit>
    @GET("form")
    suspend fun obtenerFormularios(): List<ArchivoPkm>
    @GET("form/{id}")
    suspend fun obtenerFormulario(@Path("id") id: Int): ArchivoPkm
}