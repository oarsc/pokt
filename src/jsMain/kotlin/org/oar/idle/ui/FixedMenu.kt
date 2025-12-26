package org.oar.idle.ui

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.DIV
import org.oar.idle.lib.style
import org.oar.idle.ui.stats.Stats
import org.w3c.dom.HTMLDivElement

object FixedMenu : HTMLBlock<HTMLDivElement>(DIV, id = "fixed-menu") {

    init {
        +StaticButtons
        +Stats

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
