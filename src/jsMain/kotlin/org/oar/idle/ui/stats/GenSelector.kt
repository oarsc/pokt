package org.oar.idle.ui.stats

import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.Utils.createElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLSpanElement

class GenSelector(
    private val ongenchange: (Int) -> Unit
) : HTMLObservableElement<HTMLDivElement>("div", id = "gen-selector") {

    private var currentGen by renderProperty(0, 0)
    private val maxGen = read(pokemonData)!!.maxOf { it.gen }

    private val left = createElement<HTMLImageElement>("img").apply {
        className = "left-btn"
        src = "./icon/left.svg"
    }
    private val span = createElement<HTMLSpanElement>("span")
    private val right = createElement<HTMLImageElement>("img").apply {
        className = "right-btn"
        src = "./icon/right.svg"
    }

    init {
        left.onclick = {
            if (currentGen > 0) {
                currentGen--
            }
            false
        }
        span.textContent = "All gens"
        right.onclick = {
            if (currentGen < maxGen) {
                currentGen++
            }
            false
        }

        append {
            +left
            +span
            +right
        }
    }

    override fun render(identifier: Int) {
        when(identifier) {
            0 -> {
                if (currentGen == 0) {
                    span.textContent = "All gens"
                } else {
                    span.textContent = "Gen $currentGen"
                }
                ongenchange(currentGen)
            }
        }
    }

    companion object {
        init {
            style {
                "#gen-selector" {
                }
                "#gen-selector > *" {
                    "verticalAlign" to "middle"
                }
                "#gen-selector > img" {
                    "height" to "24px"
                    "cursor" to "pointer"
                }
                "#gen-selector > span" {
                    "textAlign" to "center"
                    "width" to "100px"
                    "display" to "inline-block"
                }
            }
        }
    }
}
