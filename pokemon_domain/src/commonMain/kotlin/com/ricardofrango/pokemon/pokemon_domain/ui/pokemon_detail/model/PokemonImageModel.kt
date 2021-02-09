package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model

data class PokemonImageModel(
    private val imageUrl: String,
    val name: String,
    val pokemonUrl: String
) : IPokemonImageModel {

    override fun getImageUrl(): String {
        return imageUrl
    }
}