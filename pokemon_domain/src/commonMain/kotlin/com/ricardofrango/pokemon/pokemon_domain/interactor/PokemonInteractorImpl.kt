package com.ricardofrango.pokemon.pokemon_domain.interactor

import com.ricardofrango.pokemon.pokemon_domain.Platform
import com.ricardofrango.pokemon.pokemon_domain.interactor.models.*
import com.ricardofrango.pokemon.pokemon_domain.networking.models.*
import com.ricardofrango.pokemon.pokemon_domain.repository.PokemonRepository
import io.ktor.http.*

class PokemonInteractorImpl(
    private val pokemonRepository: PokemonRepository
) : PokemonInteractor {

    private val platform by lazy { Platform() }

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

    override suspend fun getPokemonDetails(id: Int): PokemonDetailEntity {
        val pokemonDetail = pokemonRepository.getPokemonByNumber(id)
        return buildPokemonDetails(pokemonDetail)
    }

    override suspend fun getPokemonDetails(pokemonUrl: String): PokemonDetailEntity {
        val pokemonDetail = pokemonRepository.getPokemonByUrl(pokemonUrl)
        return buildPokemonDetails(pokemonDetail)
    }

    private suspend fun buildPokemonDetails(pokemonDetail: PokemonContract): PokemonDetailEntity {
        val pokemonSpecie = pokemonRepository.getPokemonSpecie(pokemonDetail.species.url)
        val evolutionChain =
            pokemonRepository.getPokemonEvolutionChain(pokemonSpecie.evolution_chain.url)
        val varieties = mutableListOf<PokemonContract>()

        pokemonSpecie.varieties.filter { !it.is_default }.forEach {
            varieties.add(pokemonRepository.getPokemonByUrl(it.pokemon.url))
        }

        return convertToDetailsEntity(pokemonDetail, pokemonSpecie, varieties, evolutionChain)
    }

    private fun convertToEntity(pokemonDetail: PokemonContract): PokemonEntity {
        return PokemonEntity(
            name = pokemonDetail.name.capitalize(),
            number = pokemonDetail.id,
            image = getImageFromPokemonDetail(pokemonDetail),
            url = /*pokemonDetail.forms.firstOrNull()?.url
                ?: */"https://pokeapi.co/api/v2/pokemon/${pokemonDetail.id}"
        )
    }

    private suspend fun convertToDetailsEntity(
        pokemonDetail: PokemonContract,
        pokemonSpecie: PokemonSpecieDetailContract,
        varieties: MutableList<PokemonContract>,
        evolutionChain: PokemonEvolutionChainDetailContract
    ): PokemonDetailEntity {
        val pokemonColor = platform.getColorFrom(pokemonSpecie.color.name)
        return PokemonDetailEntity(
            name = pokemonDetail.name.capitalize(),
            number = pokemonDetail.id,
            images = getImagesFromPokemonDetail(pokemonDetail),
            url = pokemonDetail.forms.firstOrNull()?.url
                ?: "https://pokeapi.co/api/v2/pokemon/${pokemonDetail.id}",
            types = pokemonDetail.types.joinToString(", ") { it.type.name.capitalize() },
            evolutionChain = convertToChainEntity(evolutionChain.chain)
                .map { convertSpecieToPokemonDetail(it) },
            varieties = varieties.map { convertToEntity(it) },
            generation = pokemonSpecie.generation.name.capitalize(),
            color = pokemonColor,
            isDarkColor = platform.isDark(pokemonColor)
        )
    }

    private fun getImagesFromPokemonDetail(pokemonDetail: PokemonContract): List<ImageEntity> {
        return listOf(
            ImageEntity(
                image = pokemonDetail.sprites.other.official_artwork.front_default,
                name = "Official Artwork Front"
            ),
            ImageEntity(
                image = pokemonDetail.sprites.other.dream_world.front_default,
                name = "Dream World From"
            ),
            ImageEntity(
                image = pokemonDetail.sprites.other.dream_world.front_female,
                name = "Dream World From Female"
            ),
            ImageEntity(image = pokemonDetail.sprites.front_default, name = "Front Male"),
            ImageEntity(image = pokemonDetail.sprites.front_shiny, name = "Front Male Shiny"),
            ImageEntity(image = pokemonDetail.sprites.front_female, name = "Front Female"),
            ImageEntity(
                image = pokemonDetail.sprites.front_shiny_female,
                name = "Front Female Shiny"
            ),
            ImageEntity(image = pokemonDetail.sprites.back_default, name = "Back Male"),
            ImageEntity(image = pokemonDetail.sprites.back_shiny, name = "Back Male Shiny"),
            ImageEntity(image = pokemonDetail.sprites.back_female, name = "Back Female"),
            ImageEntity(
                image = pokemonDetail.sprites.back_shiny_female,
                name = "Back Female Shiny"
            ),
        ).filter { it.image != null }
    }

    private fun getImageFromPokemonDetail(pokemonDetail: PokemonContract): String? {
        return pokemonDetail.sprites.other.official_artwork.front_default
            ?: pokemonDetail.sprites.other.dream_world.front_default
            ?: pokemonDetail.sprites.other.dream_world.front_female
            ?: pokemonDetail.sprites.front_default ?: pokemonDetail.sprites.front_shiny
            ?: pokemonDetail.sprites.front_female ?: pokemonDetail.sprites.front_shiny_female
            ?: pokemonDetail.sprites.back_default ?: pokemonDetail.sprites.back_shiny
            ?: pokemonDetail.sprites.back_female ?: pokemonDetail.sprites.back_shiny_female
    }

    private suspend fun convertSpecieToPokemonDetail(specieContract: PokemonSpecieContract): ChainEntity {
        val pokemonSpecie = pokemonRepository.getPokemonSpecie(specieContract.url)
        val pokemonDefaultVariety = pokemonSpecie.varieties.first { it.is_default }
        val pokemonDetail = pokemonRepository.getPokemonByUrl(pokemonDefaultVariety.pokemon.url)
        return ChainEntity(
            name = pokemonDetail.name,
            image = getImageFromPokemonDetail(pokemonDetail),
            pokemonDetailUrl = pokemonDefaultVariety.pokemon.url
        )
    }

    private fun convertToChainEntity(chain: ChainContract): List<PokemonSpecieContract> {
        val result = mutableListOf<PokemonSpecieContract>()
        result.add(chain.species)

        if (chain.evolves_to.isNotEmpty()) {
            chain.evolves_to.forEach { result.addAll(convertToChainEntity(it)) }
        }

        return result
    }

}