package com.ricardofrango.pokemon.pokemon_domain.interactor

import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonEntity
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonListEntity
import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonContract
import com.ricardofrango.pokemon.pokemon_domain.repository.PokemonRepository
import io.ktor.http.*

class PokemonInteractorImpl(
    private val pokemonRepository: PokemonRepository
) : PokemonInteractor {

    override suspend fun getPokemonList(nextList: String): PokemonListEntity {
        val nextListUrl = URLBuilder(nextList).build()

        return getPokemonList(nextListUrl.parameters["offset"]?.toInt() ?: 0)
    }

    override suspend fun getPokemonList(offset: Int): PokemonListEntity {
        val pokemonsReuslt = mutableListOf<PokemonEntity>()

        val pokemons = pokemonRepository.getPokemonList(offset)

        pokemons.results.forEach {
            val pokemonDetails = getPokemonByUrl(it.url)
            pokemonsReuslt.add(pokemonDetails)
        }

        return PokemonListEntity(
            nextPageUrl = pokemons.next,
            pokemons = pokemonsReuslt
        )
    }

    private suspend fun getPokemonByUrl(path: String): PokemonEntity {
        val pokemonDetail = pokemonRepository.getPokemonByUrl(path)
        return convertToEntity(pokemonDetail)
    }

    override suspend fun getPokemon(number: Int): PokemonEntity {
        val pokemonDetail = pokemonRepository.getPokemonByNumber(number)
        return convertToEntity(pokemonDetail)
    }

    private fun convertToEntity(pokemonDetail: PokemonContract): PokemonEntity {
        return PokemonEntity(
            name = pokemonDetail.name,
            number = pokemonDetail.order,
            image = pokemonDetail.sprites.front_default,
            url = pokemonDetail.forms.firstOrNull()?.url
                ?: "https://pokeapi.co/api/v2/pokemon/${pokemonDetail.order}"
        )
    }

}