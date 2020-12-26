package com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list

import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractor
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonListEntity
import com.ricardofrango.pokemon.pokemon_domain.ui.BasePresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonsListModel
import kotlinx.coroutines.launch

class PokemonListPresenter(private val pokemonInteractor: PokemonInteractor) :
    BasePresenter<PokemonListView>() {

    override fun bindView(view: PokemonListView) {
        super.bindView(view)

        getFirstPokemonList()
    }

    private fun getFirstPokemonList() {
        launch {
            view?.loadingPokemonsList()
            try {
                view?.showPokemonsList(getPokemons(0))
            } catch (error: Exception) {
                view?.errorLoadingPokemonsList()
            }
        }
    }

    private fun getMorePokemons(nextPage: String) {
        launch {
            view?.loadingMorePokemons()
            try {
                view?.showMorePokemonsList(getPokemons(nextPage))
            } catch (error: Exception) {
                view?.errorLoadingMorePokemons()
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