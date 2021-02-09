package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model

data class PokemonImageModel(
    private val imageUrl: String,
    private val name: String
) : IPokemonImageModel {

    override fun getImageUrl(): String {
        return imageUrl
    }

    override fun getName(): String {
        return name
    }
}