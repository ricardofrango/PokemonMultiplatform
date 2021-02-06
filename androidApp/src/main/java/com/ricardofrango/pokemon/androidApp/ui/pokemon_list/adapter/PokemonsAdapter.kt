package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel
import java.lang.RuntimeException

class PokemonsAdapter(
    private val callback: IPokemonsAdapter,
    private val pokemonsList: MutableList<PokemonItemModel> = mutableListOf()
) : RecyclerView.Adapter<PokemonsAdapterViewHolder>() {

    companion object {
        private const val LOADING_MORE = 1
        private const val POKEMON = 2
    }

    private var showLoadingMore = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsAdapterViewHolder {
        return when (viewType) {
            POKEMON -> PokemonViewHolder(PokemonRowView(parent.context))
            LOADING_MORE -> LoadingMoreViewHolder(LoadingMoreRowView(parent.context))
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: PokemonsAdapterViewHolder, position: Int) {
        when(holder.itemViewType) {
            POKEMON -> {
                (holder as PokemonViewHolder).bind(pokemonsList[position]) { itemClicked, posClicked, viewClicked ->
                    callback.onPokemonClicked(itemClicked, posClicked, viewClicked)
                }
            }
            LOADING_MORE -> {
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewType = if (position == pokemonsList.size) LOADING_MORE else POKEMON
        return viewType
    }

    override fun getItemCount(): Int {
        return if (showLoadingMore) pokemonsList.size + 1
        else pokemonsList.size
    }

    fun setItems(items: List<PokemonItemModel>) {
        pokemonsList.clear()
        pokemonsList.addAll(items)
        notifyItemRangeChanged(0, pokemonsList.size)
    }

    fun addItems(items: List<PokemonItemModel>) {
        showLoadingMore = false
        notifyItemRemoved(pokemonsList.size)
        val initialPos = pokemonsList.size
        pokemonsList.addAll(items)
        notifyItemRangeInserted(initialPos, items.size)
    }

    fun showLoadingMore() {
        showLoadingMore = true
        notifyItemInserted(pokemonsList.size + 1)
    }

    fun isLoadingMore(): Boolean {
        return showLoadingMore
    }

    fun hideLoadingMore() {
        showLoadingMore = false
        notifyItemRemoved(pokemonsList.size + 1)
    }
}