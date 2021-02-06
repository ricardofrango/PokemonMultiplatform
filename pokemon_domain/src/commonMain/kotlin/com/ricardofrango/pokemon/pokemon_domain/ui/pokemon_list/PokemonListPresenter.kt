package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list

import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractor
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonListEntity
import com.ricardofrango.pokemon.pokemon_domain.ui.BasePresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonsListModel
import kotlinx.coroutines.launch

class PokemonListPresenter(private val pokemonInteractor: PokemonInteractor) :
    BasePresenter<PokemonListView>() {

    private var alertedForEndOfList: Boolean = false
    private var nextPage: String? = null

    override fun bindView(view: PokemonListView) {
        super.bindView(view)

        getFirstPokemonList()
    }

    private fun getFirstPokemonList() {
        launch {
            view?.loadingPokemonsList()
            try {
                val pokemons = getPokemons(0)
                view?.showPokemonsList(pokemons)
                nextPage = pokemons.nextPage
            } catch (error: Exception) {
                view?.errorLoadingPokemonsList()
            }
        }
    }

    fun getMorePokemons() {
        nextPage?.let {
            launch {
                view?.loadingMorePokemons()
                try {
                    val pokemons = getPokemons(it)
                    view?.showMorePokemonsList(pokemons)
                    nextPage = pokemons.nextPage
                } catch (error: Exception) {
                    view?.errorLoadingMorePokemons()
                }
            }
        } ?: run {
            if (!alertedForEndOfList) {
                view?.noMorePokemonsToLoad()
            }
        }
    }

    private suspend fun getPokemons(offset: Int): PokemonsListModel {
        val pokemons = pokemonInteractor.getPokemonList(offset)

        return entityToModel(pokemons)
    }

    private suspend fun getPokemons(nextPage: String): PokemonsListModel {
        val pokemons = pokemonInteractor.getPokemonList(nextPage)

        return entityToModel(pokemons)
    }

    private fun entityToModel(pokemons: PokemonListEntity) =
        PokemonsListModel(
            nextPage = pokemons.nextPageUrl,
            pokemons = pokemons.pokemons.map {
                PokemonItemModel(
                    name = it.name,
                    number = it.number,
                    imageUrl = it.image
                )
            }
        )
}