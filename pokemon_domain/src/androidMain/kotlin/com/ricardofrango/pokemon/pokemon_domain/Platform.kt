package com.ricardofrango.pokemon.pokemon_domain

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}