package tr.s42.mockuimenu.ui.components

import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.client.MinecraftClient
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import java.time.Duration


object UIComponents {
    
    fun createSearchBar(width: Sizing): FlowLayout {
        return Containers.horizontalFlow(width, Sizing.fixed(30))
            .apply {
                surface(Surface.flat(0x40000000))
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
                surface(Surface.flat(0x50000000))
                padding(Insets.of(6))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                
                child(
                    Components.label(Text.literal(icon))
                        .color(Color.WHITE)
                )
            }
    }
    
    fun createTextButton(text: String, sizing: Sizing = Sizing.fixed(45)): FlowLayout {
        return Containers.horizontalFlow(sizing, Sizing.fixed(30))
            .apply {
                surface(Surface.flat(0x50000000))
                padding(Insets.of(6))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                
                child(
                    Components.label(Text.literal(text))
                        .color(Color.WHITE)
                )
            }
    }
    
    fun createNewBadge(text: String): FlowLayout {
        return Containers.horizontalFlow(Sizing.content(), Sizing.content())
            .apply {
                surface(Surface.flat(0xC0ADD8E6.toInt()))
                padding(Insets.of(2, 4, 2, 4))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                
                child(
                    Components.label(Text.literal(text))
                        .color(Color.WHITE)
                )
            }
    }
    
    
    private fun getBigIcon(icon: String): String {
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
        val card = Containers.verticalFlow(Sizing.fill(22), Sizing.fixed(140))
            .apply {
                surface(Surface.flat(0x60FFFFFF.toInt()))
                padding(Insets.of(12))
                margins(Insets.of(4))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                cursorStyle(CursorStyle.HAND)
                
                mouseEnter().subscribe {
                    surface(Surface.flat(0x80FFFFFF.toInt()))
                }

                mouseLeave().subscribe {
                    surface(Surface.flat(0x60FFFFFF.toInt()))
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

        val contentContainer = Containers.verticalFlow(Sizing.fill(100), Sizing.fill(100))
            .apply {
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                gap(4)
            }

        val bigIcon = getBigIcon(icon)
        contentContainer.child(
            Components.label(Text.literal(bigIcon))
                .apply {
                    margins(Insets.bottom(8))
                    horizontalTextAlignment(HorizontalAlignment.CENTER)
                    verticalTextAlignment(VerticalAlignment.CENTER)
                    sizing(Sizing.fixed(90), Sizing.fixed(90))
                    shadow(true)
                }
        )

        contentContainer.child(
            Components.label(Text.literal(title))
                .apply {
                    color(Color.WHITE)
                    horizontalTextAlignment(HorizontalAlignment.CENTER)
                    margins(Insets.top(4))
                    shadow(true)
                }
        )

        card.child(contentContainer)
        return card
    }
    
    fun createCategoryTab(text: String, isActive: Boolean = false): FlowLayout {
        return Containers.horizontalFlow(Sizing.fill(18), Sizing.fixed(30))
            .apply {
                surface(
                    if (isActive) Surface.flat(0x609370DB.toInt()) 
                    else Surface.flat(0x40000000)
                )
                padding(Insets.of(6))
                margins(Insets.horizontal(2))
                horizontalAlignment(HorizontalAlignment.CENTER)
                verticalAlignment(VerticalAlignment.CENTER)
                
                cursorStyle(CursorStyle.HAND)
                
                mouseEnter().subscribe {
                    if (!isActive) {
                        surface(Surface.flat(0x309370DB.toInt()))
                    }
                }
                
                mouseLeave().subscribe {
                    if (!isActive) {
                        surface(Surface.flat(0x40000000))
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
                    Components.label(Text.literal(text))
                        .color(Color.WHITE)
                )
            }
    }
}