package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model

data class PokemonDetailModel(
    val name: String,
    val number: Int,
    val image: String?,
    val url: String,
    val types: String,
    val color: Int,
    val isDarkColor: Boolean,
    val generation: String
)