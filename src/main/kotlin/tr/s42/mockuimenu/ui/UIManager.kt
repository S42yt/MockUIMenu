package tr.s42.mockuimenu.ui

import net.minecraft.client.MinecraftClient

object UIManager {
    private var isUIOpen = false
    private var currentScreen: MockUIScreen? = null

    fun toggleUI(client: MinecraftClient) {
        if (isUIOpen) {
            closeUI()
        } else {
            openUI(client)
        }
    }

    private fun openUI(client: MinecraftClient) {
        currentScreen = MockUIScreen()
        client.setScreen(currentScreen)
        isUIOpen = true
    }

    private fun closeUI() {
        currentScreen?.close()
        currentScreen = null
        isUIOpen = false
    }

    fun isOpen(): Boolean = isUIOpen
}