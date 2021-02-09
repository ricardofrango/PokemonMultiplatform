package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model

data class PokemonModel(
    private val name: String,
    val pokemonImageUrl: String,
    val pokemonDetailUrl : String
) : IPokemonImageModel {

    override fun getImageUrl(): String {
        return pokemonImageUrl
    }

    override fun getName(): String {
        return name
    }
}