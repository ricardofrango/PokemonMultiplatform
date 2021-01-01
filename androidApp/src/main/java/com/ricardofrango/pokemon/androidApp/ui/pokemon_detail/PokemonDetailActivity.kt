package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kennyc.view.MultiStateView
import com.ricardofrango.pokemon.androidApp.BaseActivity
import com.ricardofrango.pokemon.androidApp.BuildConfig
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractorImpl
import com.ricardofrango.pokemon.pokemon_domain.networking.PokemonHttpClient
import com.ricardofrango.pokemon.pokemon_domain.repository.PokemonRepositoryImpl
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.PokemonDetailPresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.PokemonDetailView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonDetailModel

class PokemonDetailActivity : BaseActivity<PokemonDetailPresenter, PokemonDetailView>(), PokemonDetailView {

    companion object {

        private const val EXTRA_POKEMON_ID = "${BuildConfig.APPLICATION_ID}.EXTRA_POKEMON_ID"

        fun startPokemonDetailActivity(context: Context, id : Int) {
            context.startActivity(Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_POKEMON_ID, id)
            })
        }
    }

    override fun createPresenter(): PokemonDetailPresenter {
        return PokemonDetailPresenter(PokemonInteractorImpl(PokemonRepositoryImpl(PokemonHttpClient())))
    }

    private val pokemonDetailMSV : MultiStateView by lazy { findViewById(R.id.msvPokemonDetail) }
    private val pokemonDetailHeader : AppCompatImageView by lazy { findViewById(R.id.ivHeader) }
    private val pokemonDetailName : AppCompatTextView by lazy { findViewById(R.id.tvPokemonName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
    }

    override fun loadingPokemonDetails() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.LOADING
    }

    override fun errorLoadingPokemonDetails() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.ERROR
    }

    override fun showPokemonDetails(pokemonDetailModel: PokemonDetailModel) {
        Glide.with(this).load(pokemonDetailModel.image).into(pokemonDetailHeader)
        pokemonDetailName.text = pokemonDetailModel.name

        pokemonDetailMSV.viewState = MultiStateView.ViewState.CONTENT
    }

    override fun getPokemonId(): Int = intent.getIntExtra(EXTRA_POKEMON_ID, 0)

    override fun wrongPokemonId() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.ERROR
    }
}