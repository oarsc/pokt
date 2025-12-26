package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.IMG
import org.oar.idle.lib.style
import org.w3c.dom.HTMLImageElement

class ToggleButton(
    src: String,
    title: String
): HTMLBlock<HTMLImageElement>(IMG, "toggle-btn") {
    var value = false
        set(value) {
            field = value
            element.classList.toggle("on", value)
        }
    var onChange: ((Boolean) -> Unit)? = null

    init {
        element.apply {
            this.src = src
            this.title = title
            this.onclick = {
                value = !value
                onChange?.invoke(value)
            }
        }
    }

    companion object {
        init {
            style {
                ".toggle-btn" {
                    "marginLeft" to "auto"
                    "cursor" to "pointer"
                    "opacity" to "0.3"
                    "display" to "block"
                }
                ".toggle-btn.on" {
                    "opacity" to "1"
                }
            }
        }
    }
}