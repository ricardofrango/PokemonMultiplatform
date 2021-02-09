package com.ricardofrango.pokemon.pokemon_domain


import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    actual fun getColorFrom(text: String): Int {
        return 0
    }

    actual fun isDark(color: Int): Boolean {
        return false
    }
}