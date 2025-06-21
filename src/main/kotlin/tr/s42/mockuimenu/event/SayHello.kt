package tr.s42.mockuimenu.event

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.GameOptions
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW


object SayHello {
    private var hasTriggered = false
    private var f3Pressed = false
    private var mPressed = false

    private val f3Key = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.mockuimenu.f3_override",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F3,
            "category.mockuimenu.keys"
        )
    )

    private val mKey = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.mockuimenu.m_override",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "category.mockuimenu.keys"
        )
    )

    fun initialize() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val currentF3 = f3Key.isPressed
            val currentM = mKey.isPressed

            if (currentF3 && currentM && !hasTriggered) {
                sendHelloMessage()
                hasTriggered = true
            }

            if (!currentF3 || !currentM) {
                hasTriggered = false
            }

            f3Pressed = currentF3
            mPressed = currentM
        }
    }

    private fun sendHelloMessage() {
        val client = MinecraftClient.getInstance()
        if (client.player != null && client.world != null) {
            val message = Text.literal("<S42_> Hallo Norisk Client!!")
            client.inGameHud.chatHud.addMessage(message)
            println("Message sent: <S42_> Hallo Norisk Client!!")
        } else {
            println("Player or world is null!")
        }
    }
}