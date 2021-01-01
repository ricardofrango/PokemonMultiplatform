package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail

import com.ricardofrango.pokemon.pokemon_domain.ui.BaseView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonDetailModel

interface PokemonDetailView : BaseView {
    fun loadingPokemonDetails()
    fun errorLoadingPokemonDetails()
    fun showPokemonDetails(pokemonDetailModel: PokemonDetailModel)
    fun getPokemonId(): Int
    fun wrongPokemonId()
}