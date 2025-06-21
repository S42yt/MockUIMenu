package tr.s42.mockuimenu.ui.components

import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.client.MinecraftClient
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import tr.s42.mockuimenu.ui.cardanimationmanager.CardAnimationManager
import tr.s42.mockuimenu.ui.marqueemanager.MarqueeManager
import kotlin.and
import kotlin.collections.fill

object UIComponents {

    fun createSearchBar(width: Sizing): FlowLayout {
        return Containers.horizontalFlow(width, Sizing.fixed(30))
            .apply {
            surface(Surface.flat(0x33000000))
            padding(Insets.of(6))
            child(
                Components.label(Text.literal("🔍 Type to search..."))
                    .color(Color.WHITE)
            )
            verticalAlignment(VerticalAlignment.CENTER)
        }
    }

    fun createIconButton(icon: String, sizing: Sizing = Sizing.fixed(35)): FlowLayout {
        return Containers.horizontalFlow(sizing, Sizing.fixed(30))
            .apply {
                surface(Surface.flat(0x40FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt())))
                padding(Insets.of(8))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.TOP)

                child(
                    Components.label(Text.literal(icon))
                        .color(Color.WHITE)
                        .margins(Insets.top(0))
                )
            }
    }

    fun createTextButton(text: String, sizing: Sizing = Sizing.fixed(45), isActive: Boolean = false): FlowLayout {
        return Containers.horizontalFlow(sizing, Sizing.fixed(30))
            .apply {
                surface(
                    if (isActive) Surface.flat(0x80FFFFFF.toInt()).and(Surface.outline(0xFFFFFFFF.toInt()))
                    else Surface.flat(0x40FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt()))
                )
                padding(Insets.of(4, 8, 2, 8))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)

                cursorStyle(CursorStyle.HAND)

                mouseEnter().subscribe {
                    if (!isActive) {
                        surface(Surface.flat(0x60FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt())))
                    }
                }

                mouseLeave().subscribe {
                    if (!isActive) {
                        surface(Surface.flat(0x40FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt())))
                    }
                }

                mouseDown().subscribe { _, _, _ ->
                    MinecraftClient.getInstance().soundManager.play(
                        net.minecraft.client.sound.PositionedSoundInstance.master(
                            SoundEvents.UI_BUTTON_CLICK, 1.0f
                        )
                    )
                    true
                }

                child(
                    Components.label(Text.literal(text).styled { it.withBold(true) })
                        .color(Color.WHITE)
                        .margins(Insets.of(0))
                        .sizing(Sizing.content(), Sizing.content(1.15f.toInt()))
                )
            }
    }

    private fun getIcon(icon: String): String {
        return when (icon) {
            "💎" -> "◆"
            "⚔" -> "⚔"
            "🛡" -> "🛡"
            "👤" -> "👤"
            "💬" -> "💬"
            "🗨" -> "💭"
            "📡" -> "📡"
            "🏷" -> "🏷"
            "📝" -> "📝"
            "🔄" -> "🔄"
            "📊" -> "📊"
            "📶" -> "📶"
            "📦" -> "📦"
            "⏰" -> "⏰"
            "🎯" -> "🎯"
            "📐" -> "📐"
            "☀" -> "☀"
            "🏆" -> "🏆"
            "📷" -> "📷"
            "📍" -> "📍"
            "🔍" -> "🔍"
            "👁" -> "👁"
            "🌫" -> "🌫"
            "📏" -> "📏"
            "⚡" -> "⚡"
            "🔧" -> "🔧"
            else -> icon
        }
    }

    fun createFeatureCard(icon: String, title: String, isNew: Boolean = false): FlowLayout {
        val originalSize = 100
        val hoverSize = 125

        val card = Containers.verticalFlow(Sizing.fixed(originalSize), Sizing.fixed(originalSize))
            .apply {
                surface(Surface.flat(0x40FFFFFF))
                padding(Insets.of(10))
                margins(Insets.of(4))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                cursorStyle(CursorStyle.HAND)

                CardAnimationManager.registerCard(this, originalSize, hoverSize)

                mouseEnter().subscribe {
                    surface(Surface.flat(0x60FFFFFF))
                    CardAnimationManager.setHoverState(this, true)
                }

                mouseLeave().subscribe {
                    surface(Surface.flat(0x40FFFFFF))
                    CardAnimationManager.setHoverState(this, false)
                }

                mouseDown().subscribe { _, _, _ ->
                    MinecraftClient.getInstance().soundManager.play(
                        net.minecraft.client.sound.PositionedSoundInstance.master(
                            SoundEvents.UI_BUTTON_CLICK, 1.0f
                        )
                    )
                    true
                }
            }

        val iconContainer = Containers.verticalFlow(Sizing.fill(100), Sizing.fill(70))
            .apply {
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
            }

        val iconText = getIcon(icon)
        iconContainer.child(
            Components.label(Text.literal(iconText))
                .apply {
                    horizontalTextAlignment(HorizontalAlignment.CENTER)
                    verticalTextAlignment(VerticalAlignment.CENTER)
                    sizing(Sizing.fixed(60), Sizing.fixed(60))
                    shadow(true)
                    color(Color.WHITE)
                }
        )

        val titleContainer = Containers.verticalFlow(Sizing.fill(100), Sizing.fill(30))
            .apply {
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                surface(Surface.flat(0x40000000))
                zIndex(100)
            }

        val maxTitleLength = 11
        val titleLabel = Components.label(Text.literal(
            if (title.length <= maxTitleLength) title else title.take(maxTitleLength)
        ).styled { it.withBold(true) })
            .apply {
                horizontalTextAlignment(HorizontalAlignment.CENTER)
                color(Color.WHITE)
                shadow(true)
            }

        if (title.length > maxTitleLength) {
            MarqueeManager.registerLabel(titleLabel, title, maxTitleLength)

            card.mouseEnter().subscribe {
                MarqueeManager.startAnimation(titleLabel)
            }

            card.mouseLeave().subscribe {
                MarqueeManager.stopAnimation(titleLabel)
            }
        }

        titleContainer.child(titleLabel)
        card.child(iconContainer)
        card.child(titleContainer)

        if (isNew) {
            val newLabel = Components.label(Text.literal("NEW").styled { it.withBold(true) })
                .apply {
                    color(Color.ofRgb(0xFFFFFF))
                    shadow(true)
                    zIndex(200)
                }

            val newLabelContainer = Containers.verticalFlow(Sizing.fixed(28), Sizing.fixed(10))
                .apply {
                    child(newLabel)
                    horizontalAlignment(HorizontalAlignment.CENTER)
                    verticalAlignment(VerticalAlignment.CENTER)
                    margins(Insets.of(5))
                    positioning(Positioning.absolute(50, 1))
                    zIndex(200)
                }

            card.child(newLabelContainer)
        }
        return card
    }

    fun createCategoryTab(text: String, isActive: Boolean = false): FlowLayout {
        return Containers.horizontalFlow(Sizing.fill(18), Sizing.fixed(26))
            .apply {
                surface(
                    if (isActive) Surface.flat(0x80FFFFFF.toInt()).and(Surface.outline(0xFFFFFFFF.toInt()))
                    else Surface.flat(0x40FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt()))
                )
                padding(Insets.of(4, 6, 4, 6))
                margins(Insets.horizontal(1))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)

                cursorStyle(CursorStyle.HAND)

                mouseEnter().subscribe {
                    if (!isActive) {
                        surface(Surface.flat(0x60FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt())))
                    }
                }

                mouseLeave().subscribe {
                    if (!isActive) {
                        surface(Surface.flat(0x40FFFFFF).and(Surface.outline(0xFFFFFFFF.toInt())))
                    }
                }

                mouseDown().subscribe { _, _, _ ->
                    MinecraftClient.getInstance().soundManager.play(
                        net.minecraft.client.sound.PositionedSoundInstance.master(
                            SoundEvents.UI_BUTTON_CLICK, 1.0f
                        )
                    )
                    true
                }

                child(
                    Components.label(Text.literal(text).styled { it.withBold(true) })
                        .color(Color.WHITE)
                        .margins(Insets.top(0))
                        .sizing(Sizing.content(), Sizing.content())
                )
            }
    }
}