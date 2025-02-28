package org.oar.idle.ui

import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.ui.boilerplate.ButtonDiv
import org.oar.idle.ui.boilerplate.InputDiv
import org.w3c.dom.HTMLDivElement

class ContentDiv : HTMLObservableElement<HTMLDivElement>("div", id = "content") {

    private val input: InputDiv
    private val button: ButtonDiv

    init {
        input = InputDiv()
        button = ButtonDiv()

        append {
            +input
            +button
            +CounterElement()
        }
    }

    companion object {
        init {
            style {
                "#content" {
                    "textAlign" to "center"
                    "backgroundColor" to "yellowgreen"
                }
            }
        }
    }
}
