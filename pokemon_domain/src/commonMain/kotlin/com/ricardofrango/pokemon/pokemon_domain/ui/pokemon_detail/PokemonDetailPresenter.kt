package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail

import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractor
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonDetailEntity
import com.ricardofrango.pokemon.pokemon_domain.ui.BasePresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonDetailModel
import kotlinx.coroutines.launch

class PokemonDetailPresenter(private val pokemonInteractor: PokemonInteractor) :
    BasePresenter<PokemonDetailView>() {

    override fun bindView(view: PokemonDetailView) {
        super.bindView(view)

        getPokemonDetailById(view.getPokemonId())
    }

    fun getPokemonDetailById(id: Int) {
        if (id <= 0) {
            view?.wrongPokemonId()
            return
        }

        launch {
            try {
                view?.loadingPokemonDetails()
                val pokemonDetail = pokemonInteractor.getPokemonDetails(id)

                val pokemonDetailModel = convertEntityToViewModel(pokemonDetail)

                view?.showPokemonDetails(pokemonDetailModel)
            } catch (error: Exception) {
                view?.errorLoadingPokemonDetails()
            }
        }
    }

    private fun convertEntityToViewModel(pokemonDetail: PokemonDetailEntity): PokemonDetailModel {
        return PokemonDetailModel(
            name = pokemonDetail.name,
            number = pokemonDetail.number,
            image = pokemonDetail.image,
            url = pokemonDetail.url
        )
    }
}
