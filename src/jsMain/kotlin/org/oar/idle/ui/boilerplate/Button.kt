package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.IMG
import org.oar.idle.lib.style
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.MouseEvent

class Button(
    src: String,
    title: String
): HTMLBlock<HTMLImageElement>(IMG, "btn") {
    var onclick: ((MouseEvent) -> Unit)? = null

    init {
        element.apply {
            this.src = src
            this.title = title
            this.onclick = {
                onclick?.invoke(it)
            }
        }
    }

    companion object {
        init {
            style {
                ".btn" {
                    "marginLeft" to "auto"
                    "cursor" to "pointer"
                    "display" to "block"
                }
            }
        }
    }
}