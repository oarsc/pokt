package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLDefinitionConstants.SPAN
import org.oar.idle.lib.style

class TitleStatElement(
    title: String,
    initValue: Int = 0,
    totalValue: Int = 0,
): StatElement(initValue, totalValue, "t") {

    init {
        +SPAN("title") {
            -title
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