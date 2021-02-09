package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail

import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractor
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonDetailEntity
import com.ricardofrango.pokemon.pokemon_domain.ui.BasePresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonDetailModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonImageModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonModel
import kotlinx.coroutines.launch

class PokemonDetailPresenter(private val pokemonInteractor: PokemonInteractor) :
    BasePresenter<PokemonDetailView>() {

    override fun bindView(view: PokemonDetailView) {
        super.bindView(view)

        val pokemonUrl = view.getPokemonUrl()

        pokemonUrl?.let { getPokemonByUrl(it) }
            ?: run { getPokemonDetailById(view.getPokemonId()) }
    }

    private fun getPokemonByUrl(pokemonUrl: String) {
        launch {
            try {
                view?.loadingPokemonDetails()
                val pokemonDetail = pokemonInteractor.getPokemonDetails(pokemonUrl)

                showPokemonDetail(pokemonDetail)
            } catch (error: Exception) {
                error.printStackTrace()
                view?.errorLoadingPokemonDetails()
            }
        }
    }

    private fun showPokemonDetail(pokemonDetail: PokemonDetailEntity) {
        val pokemonDetailModel = convertEntityToViewModel(pokemonDetail)

        view?.showPokemonDetails(pokemonDetailModel)
    }

    private fun getPokemonDetailById(id: Int) {
        if (id <= 0) {
            view?.wrongPokemonId()
            return
        }

        launch {
            try {
                view?.loadingPokemonDetails()
                val pokemonDetail = pokemonInteractor.getPokemonDetails(id)

                showPokemonDetail(pokemonDetail)
            } catch (error: Exception) {
                error.printStackTrace()
                view?.errorLoadingPokemonDetails()
            }
        }
    }

    private fun convertEntityToViewModel(pokemonDetail: PokemonDetailEntity): PokemonDetailModel {
        return PokemonDetailModel(
            name = pokemonDetail.name,
            number = pokemonDetail.number,
            images = pokemonDetail.images.filter { it.image != null && !it.image.endsWith("svg") }
                .map { PokemonImageModel(it.image!!, it.name, "") },
            url = pokemonDetail.url,
            color = pokemonDetail.color,
            isDarkColor = pokemonDetail.isDarkColor,
            types = pokemonDetail.types,
            generation = pokemonDetail.generation,
            evolutionChain = pokemonDetail.evolutionChain.filter {
                it.image != null && !it.image.endsWith(
                    "svg"
                )
            }
                .map {
                    PokemonModel(
                        pokemonImageUrl = it.image!!,
                        name = it.name,
                        pokemonDetailUrl = it.pokemonDetailUrl
                    )
                },
            varieties = pokemonDetail.varieties.filter { it.image != null && !it.image.endsWith("svg") }
                .map {
                    PokemonModel(
                        pokemonImageUrl = it.image!!,
                        name = it.name,
                        pokemonDetailUrl = it.url
                    )
                }
        )
    }
}
