package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.kennyc.view.MultiStateView
import com.ricardofrango.pokemon.androidApp.ui.BaseActivity
import com.ricardofrango.pokemon.androidApp.BuildConfig
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.adapter.IPokemonImagesAdapter
import com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.adapter.PokemonImagesAdapter
import com.ricardofrango.pokemon.pokemon_domain.interactor.PokemonInteractorImpl
import com.ricardofrango.pokemon.pokemon_domain.networking.PokemonHttpClient
import com.ricardofrango.pokemon.pokemon_domain.repository.PokemonRepositoryImpl
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.PokemonDetailPresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.PokemonDetailView
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonDetailModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonImageModel
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.PokemonModel

class PokemonDetailActivity : BaseActivity<PokemonDetailPresenter, PokemonDetailView>(),
    PokemonDetailView, IPokemonImagesAdapter<PokemonModel> {

    companion object {

        private const val EXTRA_POKEMON_ID = "${BuildConfig.APPLICATION_ID}.EXTRA_POKEMON_URL"
        private const val EXTRA_POKEMON_URL = "${BuildConfig.APPLICATION_ID}.EXTRA_POKEMON_ID"

        fun startPokemonDetailActivity(context: Context, id: Int) {
            context.startActivity(Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_POKEMON_ID, id)
            })
        }

        fun startPokemonDetailActivity(context: Context, url: String) {
            context.startActivity(Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_POKEMON_URL, url)
            })
        }
    }

    override fun createPresenter(): PokemonDetailPresenter {
        return PokemonDetailPresenter(PokemonInteractorImpl(PokemonRepositoryImpl(PokemonHttpClient())))
    }

    private val imagesAdapter by lazy { PokemonImagesAdapter<PokemonImageModel>(true) }
    private val evolutionChainAdapter by lazy { PokemonImagesAdapter<PokemonModel>(false, this) }
    private val varietiesAdapter by lazy { PokemonImagesAdapter<PokemonModel>(false, this) }

    private val pokemonDetailMSV: MultiStateView by lazy { findViewById(R.id.msvPokemonDetail) }
    private val pokemonDetailImagesPager: ViewPager2 by lazy { findViewById(R.id.ivHeaderImages) }
    private val pokemonDetailNumber: AppCompatTextView by lazy { findViewById(R.id.tvPokemonNumber) }
    private val pokemonNumberBackground: AppCompatImageView by lazy { findViewById(R.id.ivPokemonNumberBackground) }
    private val pokemonGeneration: AppCompatTextView by lazy { findViewById(R.id.tvPokemonGeneration) }
    private val pokemonType: AppCompatTextView by lazy { findViewById(R.id.tvPokemonType) }
    private val pokemonEvolutionChain: RecyclerView by lazy { findViewById(R.id.rvEvolutionChain) }
    private val pokemonVarieties: RecyclerView by lazy { findViewById(R.id.rvVarieties) }
    private val pokemonGroupVarieties: Group by lazy { findViewById(R.id.gVarieties) }
    private val pokemonGroupEvolutionChain: Group by lazy { findViewById(R.id.gEvolutionChain) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        supportActionBar?.hide()

        setupView()
    }

    private fun setupView() {
        pokemonDetailImagesPager.run {
            adapter = imagesAdapter
        }
        pokemonEvolutionChain.run {
            adapter = evolutionChainAdapter
            layoutManager =
                LinearLayoutManager(this@PokemonDetailActivity, RecyclerView.HORIZONTAL, false)
        }
        pokemonVarieties.run {
            adapter = varietiesAdapter
            layoutManager =
                LinearLayoutManager(this@PokemonDetailActivity, RecyclerView.HORIZONTAL, false)
        }
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

        pokemonDetailModel.evolutionChain.takeIf { it.isNotEmpty() }?.let {
            evolutionChainAdapter.setImages(it)
            pokemonGroupEvolutionChain.visibility = VISIBLE
        } ?: run { pokemonGroupEvolutionChain.visibility = GONE }

        pokemonDetailModel.varieties.takeIf { it.isNotEmpty() }?.let {
            varietiesAdapter.setImages(it)
            pokemonGroupVarieties.visibility = VISIBLE
        } ?: run { pokemonGroupVarieties.visibility = GONE }

        pokemonDetailModel.images.takeIf { it.isNotEmpty() }?.let {
            imagesAdapter.setImages(it)
            pokemonDetailImagesPager.visibility = VISIBLE
        } ?: run { pokemonDetailImagesPager.visibility = INVISIBLE }

        val color = if (pokemonDetailModel.isDarkColor) R.color.white else R.color.black

        pokemonDetailNumber.setTextColor(ContextCompat.getColor(this, color))
        pokemonDetailNumber.text = "${pokemonDetailModel.number}"
        ImageViewCompat.setImageTintList(pokemonNumberBackground, ColorStateList.valueOf(pokemonDetailModel.color))

        pokemonType.text = pokemonDetailModel.types
        pokemonGeneration.text = pokemonDetailModel.generation

        pokemonDetailMSV.viewState = MultiStateView.ViewState.CONTENT
    }

    override fun getPokemonId(): Int = intent.getIntExtra(EXTRA_POKEMON_ID, 0)
    override fun getPokemonUrl(): String? = intent.getStringExtra(EXTRA_POKEMON_URL)

    override fun wrongPokemonId() {
        pokemonDetailMSV.viewState = MultiStateView.ViewState.ERROR
    }

    override fun onItemClicked(positionClicked: Int, itemClicked: PokemonModel, viewClicked: View) {
        PokemonDetailActivity.startPokemonDetailActivity(this, itemClicked.pokemonDetailUrl)
    }
}