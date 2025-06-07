package org.oar.idle.ui.pokemon

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.model.PokemonData
import org.w3c.dom.HTMLImageElement

class PokeImage(
    private val pokemonData: PokemonData
): HTMLObservableElement<HTMLImageElement>("img", className = "pk-img") {

    init {
        element.apply {
            src = "./img/${pokemonData.key}.png"
            title = pokemonData.name

            onerror = { _, _, _, _, _ ->
                style.display = "none"
                console.log("Failed loading image \"${pokemonData.key}\"")
            }
        }
    }

    companion object {
        init {
            style {
                ".zoom .pk-img" {
                    // original 96x96
                    "width" to "192px"
                    "height" to "192px"
                    "imageRendering" to "pixelated"
                }
            }
        }
    }
}