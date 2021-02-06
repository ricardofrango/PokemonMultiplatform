package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
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
    private val pokemonDetailNumber : AppCompatTextView by lazy { findViewById(R.id.tvPokemonNumber) }
    private val pokemonNumberBackground : AppCompatImageView by lazy { findViewById(R.id.ivPokemonNumberBackground) }
    private val pokemonGeneration : AppCompatTextView by lazy { findViewById(R.id.tvPokemonGeneration) }
    private val pokemonType : AppCompatTextView by lazy { findViewById(R.id.tvPokemonType) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        supportActionBar?.hide()
    }

    override fun loadingPokemonDetails() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.LOADING
    }

    override fun errorLoadingPokemonDetails() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.ERROR
    }

    @SuppressLint("SetTextI18n")
    override fun showPokemonDetails(pokemonDetailModel: PokemonDetailModel) {
        supportActionBar?.title = pokemonDetailModel.name
        supportActionBar?.show()

        pokemonDetailModel.image?.let {
            pokemonDetailHeader.visibility = VISIBLE
            Glide.with(this).load(it).into(pokemonDetailHeader)
        } ?: run { pokemonDetailHeader.visibility = GONE }

        val color = if (pokemonDetailModel.isDarkColor) R.color.white else R.color.black

        pokemonDetailNumber.setTextColor(resources.getColor(color, theme))
        pokemonDetailNumber.text = "${pokemonDetailModel.number}"
        pokemonNumberBackground.imageTintList = ColorStateList.valueOf(pokemonDetailModel.color)

        pokemonType.text = pokemonDetailModel.types
        pokemonGeneration.text = pokemonDetailModel.generation

        pokemonDetailMSV.viewState = MultiStateView.ViewState.CONTENT
    }

    override fun getPokemonId(): Int = intent.getIntExtra(EXTRA_POKEMON_ID, 0)

    override fun wrongPokemonId() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.ERROR
    }
}