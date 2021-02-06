package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list

import com.ricardofrango.pokemon.pokemon_domain.ui.BaseView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonsListModel

interface PokemonListView : BaseView {
    fun loadingPokemonsList()
    fun loadingMorePokemons()
    fun showPokemonsList(pokemonListModel: PokemonsListModel)
    fun showMorePokemonsList(pokemons: PokemonsListModel)
    fun errorLoadingPokemonsList()
    fun errorLoadingMorePokemons()
    fun noMorePokemonsToLoad()
}