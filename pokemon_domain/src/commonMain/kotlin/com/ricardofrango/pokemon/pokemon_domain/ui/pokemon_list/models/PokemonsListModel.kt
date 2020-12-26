package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models

data class PokemonsListModel(
    val nextPage : String?,
    val pokemons : List<PokemonItemModel>
)