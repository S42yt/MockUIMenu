package tr.s42.mockuimenu.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW
import tr.s42.mockuimenu.event.SayHello
import tr.s42.mockuimenu.ui.MockUIScreen

class MockUIMenuClient : ClientModInitializer {
    companion object {
        lateinit var TOGGLE_UI_KEY: KeyBinding
    }

    override fun onInitializeClient() {
        TOGGLE_UI_KEY = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.mockuimenu.toggle_ui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "category.mockuimenu.keys"
            )
        )

        SayHello.initialize()

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            while (TOGGLE_UI_KEY.wasPressed()) {
                client.setScreen(MockUIScreen())
            }
        }
    }
}