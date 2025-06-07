package org.oar.idle.ui.pokemon

import kotlinx.browser.localStorage
import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.model.PokemonData
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.set

class PokeDiv(
    pokemonData: PokemonData
): HTMLObservableElement<HTMLDivElement>("div", className = "pokemon-panel") {

    private val image = PokeImage(pokemonData)

    init {
        element.apply {
            pokemonData.setListener {
                classList.toggle("discarded", it)
            }
            if (pokemonData.discarded) {
                classList.add("discarded")
            }
            onclick = {
                pokemonData.discarded = !pokemonData.discarded
                saveDiscards()
            }
        }

        append {
            +image
        }
    }

    private fun saveDiscards() {
        val discards = read(pokemonData)!!.mapIndexedNotNull { idx, it ->
            if (it.discarded) idx else null
        }.let { JSON.stringify(it) }
        localStorage["discards"] = discards
    }

    companion object {
        init {
            style {
                ".pokemon-panel" {
                    "cursor" to "pointer"
                    "display" to "inline-block"
                }
                ".pokemon-panel.discarded" {
                    "display" to "none"
                }
                ".restore .pokemon-panel.discarded" {
                    "display" to "inline-block"
                    "opacity" to "0.5"
                    "filter" to "grayscale(1)"
                }
            }
        }
    }
}
