package org.oar.idle.ui.boilerplate

import kotlinx.browser.window
import org.oar.idle.constants.ExportId.inputId
import org.oar.idle.custom.HTMLObservableElement
import org.w3c.dom.HTMLButtonElement

class ButtonDiv(
): HTMLObservableElement<HTMLButtonElement>("button") {

    init {
        element.apply {
            textContent = "SUBMIT"
            onclick = { window.alert("HOLA MI GENTE LATINO: "+ read(inputId)) }
        }
    }
}