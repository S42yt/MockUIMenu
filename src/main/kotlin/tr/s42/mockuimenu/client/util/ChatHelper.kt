package tr.s42.mockuimenu.client.util

import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object ChatHelper {
    fun sendMessage(message: String) {
        val client = MinecraftClient.getInstance()
        client.player?.sendMessage(Text.literal(message), false)
    }
}