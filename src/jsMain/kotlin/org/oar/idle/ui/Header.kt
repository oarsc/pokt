package org.oar.idle.ui

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.Utils.createElement
import org.oar.idle.custom.style
import org.oar.idle.ui.stats.Stats
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.HTMLParagraphElement

object Header : HTMLObservableElement<HTMLHeadingElement>("h1", id = "header") {

    private val subtitle = createElement<HTMLParagraphElement>("p")

    init {
        element.textContent = "Pokemon cleaning"
        subtitle.id = "subheader"
        subtitle.textContent = "If you had to build your own Pokedex, which Pokemon would you exclude?"

        append {
            +subtitle
        }

        style {
            "#header" {
                "textAlign" to "center"
            }
            "#subheader" {
                "fontWeight" to "normal"
                "color" to "gray"
                "margin" to "0"
                "fontSize" to "0.55em"
            }
        }
    }
}
