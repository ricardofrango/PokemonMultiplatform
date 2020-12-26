package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail

import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractor
import com.ricardofrango.pokemon.pokemon_domain.ui.BasePresenter

class PokemonDetailPresenter(private val pokemonInteractor: PokemonInteractor) :
    BasePresenter<PokemonDetailView>() {

}
