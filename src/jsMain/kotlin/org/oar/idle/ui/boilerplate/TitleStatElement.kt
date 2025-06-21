package org.oar.idle.ui.boilerplate

import org.oar.idle.custom.Utils.createElement
import org.oar.idle.custom.style
import org.w3c.dom.HTMLSpanElement

class TitleStatElement(
    title: String,
    initValue: Int = 0,
    totalValue: Int = 0,
): StatElement(initValue, totalValue, "t") {

    private val titleElement = createElement<HTMLSpanElement>("span")

    init {
        titleElement.apply {
            textContent = title
            className = "title"
        }
        append {
            +titleElement
        }
        appendElements()
    }

    companion object {
        init {
            style {
                ".stat.t" {
                    "margin-bottom" to "4px"
                }
                ".stat.t .title::after" {
                    "content" to "\": \""
                }
            }
        }
    }
}