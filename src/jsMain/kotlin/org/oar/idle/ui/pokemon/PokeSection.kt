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
                    "backgroundColor" to "#ddd"
                    "margin" to "20px 0"
                    "padding" to "20px"
                    "border" to "solid black"
                    "borderWidth" to "1px 0"
                    "borderRadius" to "5px"
                    "lineHeight" to "0"
                }
            }
        }
    }
}
