package org.oar.idle.constants

object ExportId {
    val inputId = object : ExportId<String>() {}

    open class ExportId<T: Any> internal constructor()
}