package com.ricardofrango.pokemon.androidApp.ui.pokemon_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennyc.view.MultiStateView
import com.ricardofrango.pokemon.androidApp.BaseActivity
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.PokemonDetailActivity
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

    private val pokemonList: RecyclerView by lazy { findViewById(R.id.rvPokemonsList) }
    private val pokemonsMSV: MultiStateView by lazy { findViewById(R.id.msvPokemonsList) }
    private val pokemonsListAdapter: PokemonsAdapter by lazy { PokemonsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons_list)

        pokemonList.run {
            adapter = pokemonsListAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() > pokemonsListAdapter.itemCount - 3 && !pokemonsListAdapter.isLoadingMore()) {
                        presenter.getMorePokemons()
                    }
                }
            })
        }
    }

    override fun loadingPokemonsList() {
        pokemonsMSV.viewState = MultiStateView.ViewState.LOADING
    }

    override fun loadingMorePokemons() {
        pokemonsListAdapter.showLoadingMore()
    }

    override fun showPokemonsList(pokemonListModel: PokemonsListModel) {
        pokemonsListAdapter.setItems(pokemonListModel.pokemons)
        pokemonsMSV.viewState = MultiStateView.ViewState.CONTENT
    }

    override fun showMorePokemonsList(pokemons: PokemonsListModel) {
        pokemonsListAdapter.addItems(pokemons.pokemons)
    }

    override fun errorLoadingPokemonsList() {
        pokemonsMSV.viewState = MultiStateView.ViewState.ERROR
    }

    override fun errorLoadingMorePokemons() {
        pokemonsListAdapter.hideLoadingMore()
    }

    override fun noMorePokemonsToLoad() {

    }

    override fun onPokemonClicked(
        itemClicked: PokemonItemModel,
        posClicked: Int,
        viewClicked: View
    ) {
        PokemonDetailActivity.startPokemonDetailActivity(this, itemClicked.number)
    }
}