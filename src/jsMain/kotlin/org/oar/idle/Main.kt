package org.oar.idle

import kotlinx.browser.document
import org.oar.idle.custom.style
import org.oar.idle.ui.ContentDiv

fun main() {
    style {
        "body" {
            "margin" to "0"
        }
    }
    document.body!!.appendChild(ContentDiv().element)
}
