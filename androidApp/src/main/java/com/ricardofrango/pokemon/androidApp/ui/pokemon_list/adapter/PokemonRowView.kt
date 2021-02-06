package com.ricardofrango.pokemon.androidApp.ui.pokemon_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_VERTICAL
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.androidApp.ui.pokemon_list.ListMode
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_list.models.PokemonItemModel

class PokemonRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val pokemonImage: AppCompatImageView by lazy { findViewById(R.id.ivPokemonImage) }
    private val pokemonName: AppCompatTextView by lazy { findViewById(R.id.tvPokemonName) }
    private val pokemonNumber: AppCompatTextView by lazy { findViewById(R.id.tvPokemonNumber) }

    init {
        inflate(context, R.layout.row_pokemon, this)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WRAP_CONTENT)
    }

    @SuppressLint("SetTextI18n")
    fun bind(pokemonItemModel: PokemonItemModel, listMode: ListMode) {
        Glide.with(context).load(pokemonItemModel.imageUrl).into(pokemonImage)

        when(listMode) {
            ListMode.GRID -> {
                gravity = CENTER
                orientation = VERTICAL
                pokemonName.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                pokemonNumber.visibility = GONE

                pokemonName.text = "${pokemonItemModel.number} - ${pokemonItemModel.name}"
            }
            else -> {
                gravity = CENTER_VERTICAL
                orientation = HORIZONTAL
                pokemonName.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                pokemonNumber.visibility = VISIBLE

                pokemonNumber.text = "${pokemonItemModel.number}"
                pokemonName.text = pokemonItemModel.name
            }
        }
    }

}
