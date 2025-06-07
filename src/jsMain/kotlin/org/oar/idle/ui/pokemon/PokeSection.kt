package org.oar.idle.ui.pokemon

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.model.PokemonData
import org.w3c.dom.HTMLDivElement

class PokeSection(
    data: Array<PokemonData>
) : HTMLObservableElement<HTMLDivElement>("div", className = "poke-section") {

    init {
        append {
            data.forEachIndexed { idx, it ->
                +PokeDiv(it)
            }
        }
    }

    companion object {
        init {
            style {
                ".poke-section" {
                    "backgroundColor" to "yellowgreen"
                    "border-bottom" to "2px solid black"
                }
                ".poke-section:first-child" {
                    "border-top" to "2px solid black"
                }
            }
        }
    }
}
