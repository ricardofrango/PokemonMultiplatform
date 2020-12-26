package com.ricardofrango.pokemon.androidApp.ui.pokemon_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kennyc.view.MultiStateView
import com.ricardofrango.pokemon.androidApp.BaseActivity
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter.IPokemonsAdapter
import com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter.PokemonsAdapter
import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractorImpl
import com.ricardofrango.pokemon.pokemon_domain.networking.PokemonHttpClient
import com.ricardofrango.pokemon.pokemon_domain.repository.PokemonRepositoryImpl
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.PokemonListPresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.PokemonListView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonsListModel

class PokemonsListActivity : BaseActivity<PokemonListPresenter, PokemonListView>(), PokemonListView,
    IPokemonsAdapter {

    override fun createPresenter(): PokemonListPresenter {
        return PokemonListPresenter(
            pokemonInteractor = PokemonInteractorImpl(
                pokemonRepository = PokemonRepositoryImpl(
                    pokemonHttpClient = PokemonHttpClient()
                )
            )
        )
    }

    private val pokemonList : RecyclerView by lazy { findViewById(R.id.rvPokemonsList) }
    private val pokemonsMSV : MultiStateView by lazy { findViewById(R.id.msvPokemonsList) }
    private val pokemonsListAdapter : PokemonsAdapter by lazy { PokemonsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons_list)

        pokemonList.adapter = pokemonsListAdapter
    }

    override fun loadingPokemonsList() {
        pokemonsMSV.viewState = MultiStateView.ViewState.LOADING
    }

    override fun loadingMorePokemons() {

    }

    override fun showPokemonsList(pokemonListModel: PokemonsListModel) {
        pokemonsListAdapter.setItems(pokemonListModel.pokemons)
        pokemonsMSV.viewState = MultiStateView.ViewState.CONTENT
    }

    override fun showMorePokemonsList(pokemons: PokemonsListModel) {

    }

    override fun errorLoadingPokemonsList() {
        pokemonsMSV.viewState = MultiStateView.ViewState.ERROR
    }

    override fun errorLoadingMorePokemons() {

    }

    override fun onPokemonClicked(
        itemClicked: PokemonItemModel,
        posClicked: Int,
        viewClicked: View
    ) {

    }
}