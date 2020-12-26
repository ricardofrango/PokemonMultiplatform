package com.ricardofrango.pokemon.pokemon_domain.repository

import com.ricardofrango.pokemon.pokemon_domain.networking.PokemonHttpClient
import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonContract
import com.ricardofrango.pokemon.pokemon_domain.networking.models.PokemonListContract

class PokemonRepositoryImpl(private val pokemonHttpClient: PokemonHttpClient) : PokemonRepository {

    override suspend fun getPokemonList(offset : Int) : PokemonListContract {
        return pokemonHttpClient.getPokemons(offset)
    }

    override suspend fun getPokemonByNumber(number: Int): PokemonContract {
        return pokemonHttpClient.getPokemonByNumber(number)
    }

    override suspend fun getPokemonByUrl(path: String): PokemonContract {
        return pokemonHttpClient.getPokemonByPath(path)
    }
}