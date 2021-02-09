package com.ricardofrango.pokemon.pokemon_domain.interactor.models

data class PokemonDetailEntity(
    val name: String,
    val number: Int,
    val images: List<ImageEntity>,
    val url: String,
    val types: String,
    val evolutionChain: List<ChainEntity>,
    val varieties: List<PokemonEntity>,
    val generation: String,
    val color : Int,
    val isDarkColor : Boolean
)