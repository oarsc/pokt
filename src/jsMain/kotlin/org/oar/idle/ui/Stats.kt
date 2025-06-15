package org.oar.idle.ui

import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.constants.NotifierId.discardModification
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.ui.boilerplate.StatElement
import org.w3c.dom.HTMLDivElement

object Stats : HTMLObservableElement<HTMLDivElement>("div", id = "buttons") {

    private val data = read(pokemonData)!!

    private val totalPokes = StatElement("Pokes")

    init {

        listen(discardModification) {
            update()
        }
        update()

        append {
            +totalPokes
        }

        style {
//                "#buttons" {
//                    "position" to "fixed"
//                    "top" to "20px"
//                    "bottom" to "0"
//                    "right" to "20px"
//                }
        }
    }

    private fun update() {
        totalPokes.value = calculateTotalPokes()
    }

    private fun calculateTotalPokes(): Int =
        data.filter { !it.discarded }.map { it.number }.distinct().size
}
