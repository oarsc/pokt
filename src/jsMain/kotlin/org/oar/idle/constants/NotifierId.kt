package org.oar.idle.constants

object NotifierId {
    val discardModification = object : NotifierId() {}

    open class NotifierId internal constructor()
}