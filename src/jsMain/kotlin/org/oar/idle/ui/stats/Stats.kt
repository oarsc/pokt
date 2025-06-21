package org.oar.idle.ui.stats

import org.oar.idle.constants.ExportId.pokemonData
import org.oar.idle.constants.NotifierId.discardModification
import org.oar.idle.custom.HTMLObservableElement
import org.oar.idle.custom.style
import org.oar.idle.model.PokemonData
import org.oar.idle.model.Type
import org.oar.idle.ui.boilerplate.ImageStatElement
import org.oar.idle.ui.boilerplate.StatElement
import org.oar.idle.ui.boilerplate.TitleStatElement
import org.w3c.dom.HTMLDivElement

object Stats : HTMLObservableElement<HTMLDivElement>("div", id = "buttons") {

    private val data = read(pokemonData)!!

    private val totalPokes = TitleStatElement("Pokes")
    private val normalPokes = ImageStatElement("./icon/type/normal.png")
    private val firePokes = ImageStatElement("./icon/type/fire.png")
    private val waterPokes = ImageStatElement("./icon/type/water.png")
    private val electricPokes = ImageStatElement("./icon/type/electric.png")
    private val grassPokes = ImageStatElement("./icon/type/grass.png")
    private val icePokes = ImageStatElement("./icon/type/ice.png")
    private val fightingPokes = ImageStatElement("./icon/type/fighting.png")
    private val poisonPokes = ImageStatElement("./icon/type/poison.png")
    private val groundPokes = ImageStatElement("./icon/type/ground.png")
    private val flyingPokes = ImageStatElement("./icon/type/flying.png")
    private val psychicPokes = ImageStatElement("./icon/type/psychic.png")
    private val bugPokes = ImageStatElement("./icon/type/bug.png")
    private val rockPokes = ImageStatElement("./icon/type/rock.png")
    private val ghostPokes = ImageStatElement("./icon/type/ghost.png")
    private val dragonPokes = ImageStatElement("./icon/type/dragon.png")
    private val darkPokes = ImageStatElement("./icon/type/dark.png")
    private val steelPokes = ImageStatElement("./icon/type/steel.png")
    private val fairyPokes = ImageStatElement("./icon/type/fairy.png")
    private var selectedGen = 0

    init {
        listen(discardModification) {
            update()
        }
        update()

        val genSelector = GenSelector {
            selectedGen = it
            update()
        }

        append {
            +genSelector
            +totalPokes
            +normalPokes
            +firePokes
            +waterPokes
            +electricPokes
            +grassPokes
            +icePokes
            +fightingPokes
            +poisonPokes
            +groundPokes
            +flyingPokes
            +psychicPokes
            +bugPokes
            +rockPokes
            +ghostPokes
            +dragonPokes
            +darkPokes
            +steelPokes
            +fairyPokes
        }

        style {
//                "#buttons" {
//                    "position" to "fixed"
//                    "top" to "20px"
//                    "bottom" to "0"
//                    "right" to "20px"
//                }
        }
    }

    private fun update() {
        totalPokes.calculateTotalPokes()

        normalPokes.calculateTypedPokes(Type.NORMAL)
        firePokes.calculateTypedPokes(Type.FIRE)
        waterPokes.calculateTypedPokes(Type.WATER)
        electricPokes.calculateTypedPokes(Type.ELECTRIC)
        grassPokes.calculateTypedPokes(Type.GRASS)
        icePokes.calculateTypedPokes(Type.ICE)
        fightingPokes.calculateTypedPokes(Type.FIGHTING)
        poisonPokes.calculateTypedPokes(Type.POISON)
        groundPokes.calculateTypedPokes(Type.GROUND)
        flyingPokes.calculateTypedPokes(Type.FLYING)
        psychicPokes.calculateTypedPokes(Type.PSYCHIC)
        bugPokes.calculateTypedPokes(Type.BUG)
        rockPokes.calculateTypedPokes(Type.ROCK)
        ghostPokes.calculateTypedPokes(Type.GHOST)
        dragonPokes.calculateTypedPokes(Type.DRAGON)
        darkPokes.calculateTypedPokes(Type.DARK)
        steelPokes.calculateTypedPokes(Type.STEEL)
        fairyPokes.calculateTypedPokes(Type.FAIRY)
    }

    private fun StatElement.calculateTotalPokes() {
        total = data.filter { selectedGen in listOf(0, it.gen) }.uniqueNumbers()
        value = data.filter { !it.discarded && selectedGen in listOf(0, it.gen) }.uniqueNumbers()
    }

    private fun StatElement.calculateTypedPokes(type: Type) {
        total = data
            .filter { selectedGen in listOf(0, it.gen) && type in it.types }
            .uniqueNumbers()

        value = data
            .filter { !it.discarded && selectedGen in listOf(0, it.gen) && type in it.types }
            .uniqueNumbers()
    }

    private fun List<PokemonData>.uniqueNumbers() = map { it.number }.distinct().size
}
