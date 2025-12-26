package org.oar.idle.ui.pokemon

import kotlinx.browser.localStorage
import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.DIV
import org.oar.idle.lib.style
import org.oar.idle.model.PokemonData
import org.oar.idle.utils.Export.pokemonData
import org.oar.idle.utils.Notifier.discardModification
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.set

class PokeDiv(
    pokemonData: PokemonData
): HTMLBlock<HTMLDivElement>(DIV, className = "pokemon-panel") {

    init {
        element.apply {
            if (pokemonData.discarded) {
                classList.add("discarded")
            }
            onclick = {
                pokemonData.discarded = !pokemonData.discarded
                classList.toggle("discarded", pokemonData.discarded)
                saveDiscards()
                notify(discardModification)
            }
        }

        +PokeImage(pokemonData)
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
                ".pokemon-panel:hover" {
                    "backgroundColor" to "#00000010"
                    "borderRadius" to "5px"
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
