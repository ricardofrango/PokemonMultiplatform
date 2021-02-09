package com.ricardofrango.pokemon.androidApp.ui.pokemon_detail.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.ricardofrango.pokemon.androidApp.R
import com.ricardofrango.pokemon.pokemon_domain.ui.pokemon_detail.model.IPokemonImageModel


class ImageRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val pokemonImage: AppCompatImageView by lazy { findViewById(R.id.ivPokemonImage) }

    init {
        inflate(context, R.layout.row_pokemon_image, this)
    }

    fun <T : IPokemonImageModel> bind(item: T, widthMatchParent: Boolean) {
        layoutParams =
            LayoutParams(if (widthMatchParent) MATCH_PARENT else WRAP_CONTENT, MATCH_PARENT)
        pokemonImage.layoutParams =
            LayoutParams(if (widthMatchParent) MATCH_PARENT else WRAP_CONTENT, MATCH_PARENT)

        Glide.with(this).load(item.getImageUrl()).into(pokemonImage)
    }
}
