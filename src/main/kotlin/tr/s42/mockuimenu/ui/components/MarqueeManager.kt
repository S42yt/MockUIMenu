package tr.s42.mockuimenu.ui.marqueemanager

import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.core.*
import net.minecraft.text.Text
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

object MarqueeManager {
    private val labels = mutableMapOf<LabelComponent, MarqueeData>()
    private var isInitialized = false

    data class MarqueeData(
        val fullText: String,
        val maxLength: Int,
        var position: Int = 0,
        var tick: Int = 0,
        var isAnimating: Boolean = false
    )

    fun registerLabel(label: LabelComponent, text: String, maxLength: Int) {
        val paddedText = "$text    $text"
        labels[label] = MarqueeData(
            fullText = paddedText,
            maxLength = maxLength
        )

        if (!isInitialized) initialize()
    }

    fun unregisterAll() {
        labels.clear()
    }

    private fun initialize() {
        ClientTickEvents.END_CLIENT_TICK.register { updateLabels() }
        isInitialized = true
    }

    private fun updateLabels() {
        val TICKS_PER_MOVE = 3

        for ((label, data) in labels) {
            if (data.isAnimating) {
                data.tick++
                if (data.tick >= TICKS_PER_MOVE) {
                    data.tick = 0

                    data.position = (data.position + 1) % (data.fullText.length - data.maxLength)
                    val visiblePart = data.fullText.substring(data.position, data.position + data.maxLength)
                    label.text(Text.literal(visiblePart).styled { it.withBold(true) })
                }
            }
        }
    }

    fun startAnimation(label: LabelComponent) {
        labels[label]?.isAnimating = true
    }

    fun stopAnimation(label: LabelComponent) {
        labels[label]?.let { data ->
            data.isAnimating = false
            data.position = 0
            label.text(Text.literal(data.fullText.substring(0, data.maxLength)).styled { it.withBold(true) })
        }
    }
}