package com.example.pkm_forms_proyecto1.backend.poke_api

import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonsByRange(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonResponse
}