package org.oar.idle.ui

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.H1
import org.oar.idle.lib.HTMLDefinitionConstants.P
import org.oar.idle.lib.style
import org.w3c.dom.HTMLHeadingElement

object Header : HTMLBlock<HTMLHeadingElement>(H1, id = "header") {

    init {
        -"Pokemon cleaning"
        +P(id = "subheader") {
            -"If you had to build your own Pokedex, which Pokemon would you exclude?"
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
