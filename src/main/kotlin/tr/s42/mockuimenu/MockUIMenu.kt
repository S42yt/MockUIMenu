package tr.s42.mockuimenu

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

class MockUIMenu : ModInitializer {
    companion object {
        lateinit var TOGGLE_UI_KEY: KeyBinding
    }

    override fun onInitialize() {
    }
}