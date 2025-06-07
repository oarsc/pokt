package org.oar.idle.ui.pokemon

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.model.PokemonData
import org.w3c.dom.HTMLDivElement

class PokeContainer(
    data: Array<PokemonData>
) : HTMLObservableElement<HTMLDivElement>("div", id = "poke-content") {

    init {

        val sections = POKEMON_BREAKS.fold(mutableListOf(data)) { res, breakPoint ->
            res.apply {
                val list = removeLast().toList()
                val index = list.indexOfFirst { it.number == breakPoint }
                add(list.subList(0, index).toTypedArray())
                add(list.subList(index, list.size).toTypedArray())
            }
        }

        append {
            sections.forEach {
                +PokeSection(it)
            }
        }
    }

    companion object {
        private val POKEMON_BREAKS = listOf(152, 252, 387, 495, 650, 722, 810, 906)

        init {
            style {
                "#poke-content" {
                    "margin" to "50px auto"
                    "max-width" to "960px"
                }
            }
        }
    }
}
