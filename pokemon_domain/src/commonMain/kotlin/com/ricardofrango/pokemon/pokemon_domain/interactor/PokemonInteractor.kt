package com.ricardofrango.pokemon.pokemon_domain.interactor

import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonDetailEntity
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonEntity
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.PokemonListEntity

interface PokemonInteractor {

    suspend fun getPokemonList(nextList: String): PokemonListEntity
    suspend fun getPokemonList(offset: Int = 0): PokemonListEntity
    suspend fun getPokemon(number: Int): PokemonEntity
    suspend fun getPokemonDetails(id: Int): PokemonDetailEntity
    suspend fun getPokemonDetails(pokemonUrl: String): PokemonDetailEntity
}