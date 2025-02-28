package org.oar.idle.ui.boilerplate

import org.oar.idle.constants.ExportId.inputId
import org.oar.idle.custom.HTMLObservableElement
import org.w3c.dom.HTMLInputElement

class InputDiv: HTMLObservableElement<HTMLInputElement>("input") {

    init {
        expose(inputId) {
            element.value
        }
    }
}