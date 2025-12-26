package org.oar.idle.ui.pokemon

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.DIV
import org.oar.idle.lib.style
import org.oar.idle.model.PokemonData
import org.w3c.dom.HTMLDivElement

class PokeSection(
    data: Array<PokemonData>
) : HTMLBlock<HTMLDivElement>(DIV, className = "poke-section") {

    init {
        data.forEachIndexed { idx, it ->
            +PokeDiv(it)
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
