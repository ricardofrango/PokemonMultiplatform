package com.ricardofrango.pokemon.pokemon_domain.interactor.models

data class PokemonListEntity(
    val nextPageUrl: String?,
    val pokemons: List<PokemonEntity>
)