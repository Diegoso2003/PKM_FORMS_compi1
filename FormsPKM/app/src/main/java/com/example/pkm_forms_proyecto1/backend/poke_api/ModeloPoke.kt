package com.example.pkm_forms_proyecto1.backend.poke_api

data class PokemonResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)