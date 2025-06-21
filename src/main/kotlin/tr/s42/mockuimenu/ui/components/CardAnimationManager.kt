package tr.s42.mockuimenu.ui.cardanimationmanager

import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Sizing
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

object CardAnimationManager {
    private val animatedCards = mutableMapOf<FlowLayout, CardAnimationData>()
    private var isInitialized = false

    data class CardAnimationData(
        val originalWidth: Int,
        val originalHeight: Int,
        val hoverWidth: Int,
        val hoverHeight: Int,
        var targetWidth: Int,
        var targetHeight: Int,
        var currentWidth: Int,
        var currentHeight: Int
    )

    fun registerCard(card: FlowLayout, originalSize: Int, hoverSize: Int) {
        animatedCards[card] = CardAnimationData(
            originalWidth = originalSize,
            originalHeight = originalSize,
            hoverWidth = hoverSize,
            hoverHeight = hoverSize,
            targetWidth = originalSize,
            targetHeight = originalSize,
            currentWidth = originalSize,
            currentHeight = originalSize
        )

        if (!isInitialized) initialize()
    }

    fun setHoverState(card: FlowLayout, isHovering: Boolean) {
        val data = animatedCards[card] ?: return

        if (isHovering) {
            data.targetWidth = data.hoverWidth
            data.targetHeight = data.hoverHeight
        } else {
            data.targetWidth = data.originalWidth
            data.targetHeight = data.originalHeight
        }
    }

    private fun initialize() {
        ClientTickEvents.END_CLIENT_TICK.register { updateCards() }
        isInitialized = true
    }

    private fun updateCards() {
        val ANIMATION_SPEED = 0.6f

        for ((card, data) in animatedCards) {
            if (data.currentWidth != data.targetWidth || data.currentHeight != data.targetHeight) {
                data.currentWidth += ((data.targetWidth - data.currentWidth) * ANIMATION_SPEED).toInt()
                data.currentHeight += ((data.targetHeight - data.currentHeight) * ANIMATION_SPEED).toInt()

                if (Math.abs(data.currentWidth - data.targetWidth) <= 1) data.currentWidth = data.targetWidth
                if (Math.abs(data.currentHeight - data.targetHeight) <= 1) data.currentHeight = data.targetHeight

                card.sizing(Sizing.fixed(data.currentWidth), Sizing.fixed(data.currentHeight))
            }
        }
    }

    fun unregisterAll() {
        animatedCards.clear()
    }
}