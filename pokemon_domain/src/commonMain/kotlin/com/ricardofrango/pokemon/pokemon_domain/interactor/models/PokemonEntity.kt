package com.ricardofrango.pokemon.pokemon_domain.interactor.models

data class PokemonEntity(
    val name: String,
    val number: Int,
    val image: String?,
    val url: String
)