package org.oar.idle.ui

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLDivElement

object FixedMenu : HTMLObservableElement<HTMLDivElement>("div", id = "fixed-menu") {

    init {
        append {
            +StaticButtons
            +Stats
        }

        style {
            "#fixed-menu" {
                "position" to "fixed"
                "top" to "20px"
                "bottom" to "0"
                "right" to "20px"
            }
        }
    }
}
