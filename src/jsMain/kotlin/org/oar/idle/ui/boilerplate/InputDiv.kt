package org.oar.idle.ui.boilerplate

import org.oar.idle.lib.HTMLBlock
import org.oar.idle.lib.HTMLDefinitionConstants.INPUT
import org.oar.idle.utils.Export.inputId
import org.w3c.dom.HTMLInputElement

class InputDiv: HTMLBlock<HTMLInputElement>(INPUT) {

    init {
        expose(inputId) {
            element.value
        }
    }
}